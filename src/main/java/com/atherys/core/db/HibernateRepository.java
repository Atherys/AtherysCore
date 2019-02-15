package com.atherys.core.db;

import com.atherys.core.AtherysCore;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class HibernateRepository<T extends Identifiable<ID>, ID extends Serializable> implements Repository<T, ID> {

    protected Hashtable<ID, T> cache = new Hashtable<>();

    protected SessionFactory sessionFactory;

    protected Class<T> persistable;

    public HibernateRepository(Class<T> persistable) {
        this.persistable = persistable;
        EntityManagerFactory entityManagerFactory = AtherysCore.getEntityManagerFactory();

        if (entityManagerFactory instanceof SessionFactory) {
            this.sessionFactory = (SessionFactory) entityManagerFactory;
        } else {
            throw new IllegalStateException("JPA implementation is not Hibernate ( EMF is not instance of SessionFactory ).");
        }
    }

    private void saveOrUpdate(T entity, Session session) {
        // session.lock(entity, LockMode.PESSIMISTIC_WRITE);
        session.saveOrUpdate(entity);
        cache.put(entity.getId(), entity);
    }

    private void delete(T entity, Session session) {
        // session.lock(entity, LockMode.PESSIMISTIC_WRITE);
        session.delete(entity);
        cache.remove(entity.getId());
    }

    protected void transactionOf(Consumer<Session> sessionConsumer) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;

            try {
                transaction = session.beginTransaction();

                sessionConsumer.accept(session);

                session.flush();
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }

                e.printStackTrace();
            }

        }
    }

    protected CompletableFuture<Void> asyncTransactionOf(Consumer<Session> sessionConsumer) {
        return CompletableFuture.runAsync(() -> {
            transactionOf(sessionConsumer);
        });
    }

    @Override
    public Optional<T> findById(ID id) {
        T result;

        T cachedEntity = cache.get(id);

        if (cachedEntity == null) {
            Session session = sessionFactory.openSession();
            result = session.find(persistable, id);
            session.close();
        } else {
            result = cachedEntity;
        }

        return Optional.ofNullable(result);
    }

    @Override
    public void saveOne(T entity) {
        transactionOf(session -> saveOrUpdate(entity, session));
    }

    @Override
    public void saveAll(Collection<T> entities) {
        transactionOf(session -> entities.forEach((entity -> saveOrUpdate(entity, session))));
    }

    @Override
    public void deleteOne(T entity) {
        transactionOf(session -> delete(entity, session));
    }

    @Override
    public void deleteAll(Collection<T> entities) {
        transactionOf(session -> entities.forEach(entity -> delete(entity, session)));
    }

    @Override
    public CompletableFuture<Void> saveOneAsync(T entity) {
        return asyncTransactionOf(session -> saveOrUpdate(entity, session));
    }

    @Override
    public CompletableFuture<Void> saveAllAsync(Collection<T> entities) {
        return asyncTransactionOf(session -> entities.forEach((entity -> saveOrUpdate(entity, session))));
    }

    @Override
    public CompletableFuture<Void> deleteOneAsync(T entity) {
        return asyncTransactionOf(session -> delete(entity, session));
    }

    @Override
    public CompletableFuture<Void> deleteAllAsync(Collection<T> entities) {
        return asyncTransactionOf(session -> entities.forEach(entity -> delete(entity, session)));
    }

    @Override
    public CriteriaBuilder getCriteriaBuilder() {
        return sessionFactory.getCriteriaBuilder();
    }

    @Override
    public void execute(String jpql, Consumer<javax.persistence.Query> setParams) {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery(jpql);
            setParams.accept(query);
            query.executeUpdate();
        }
    }

    @Override
    public <R> void querySingle(String jpql, Class<R> result, Consumer<javax.persistence.Query> setParams, Consumer<Optional<R>> resultConsumer) {
        try (Session session = sessionFactory.openSession()) {
            Query<R> query = session.createQuery(jpql, result);
            setParams.accept(query);
            R r = query.getSingleResult();
            resultConsumer.accept(Optional.ofNullable(r));
        }
    }

    @Override
    public <R> void queryMultiple(String jpql, Class<R> result, Consumer<javax.persistence.Query> setParams, Consumer<Collection<R>> resultConsumer) {
        try (Session session = sessionFactory.openSession()) {
            Query<R> query = session.createQuery(jpql, result);
            setParams.accept(query);
            List<R> r = query.getResultList();
            resultConsumer.accept(r);
        }
    }

    @Override
    public <R> void querySingle(CriteriaQuery<R> query, Consumer<javax.persistence.Query> setParams, Consumer<Optional<R>> resultConsumer) {
        try (Session session = sessionFactory.openSession()) {
            Query<R> q = session.createQuery(query);
            setParams.accept(q);
            R r = q.getSingleResult();
            resultConsumer.accept(Optional.ofNullable(r));
        }
    }

    @Override
    public <R> void queryMultiple(CriteriaQuery<R> query, Consumer<javax.persistence.Query> setParams, Consumer<Collection<R>> resultConsumer) {
        try (Session session = sessionFactory.openSession()) {
            Query<R> q = session.createQuery(query);
            setParams.accept(q);
            List<R> r = q.getResultList();
            resultConsumer.accept(r);
        }
    }

    protected Map<ID, T> getCache() {
        return cache;
    }

    public void cacheAll() {
        CriteriaBuilder builder = getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(persistable);
        Root<T> variableRoot = query.from(persistable);

        query.select(variableRoot);

        queryMultiple(query, (q) -> {}, entities -> entities.forEach(entity -> {
            cache.put(entity.getId(), entity);
        }));
    }

    public void flushCache() {
        transactionOf(session -> cache.values().forEach(session::saveOrUpdate));
    }
}
