package com.atherys.core.db;

import com.atherys.core.AtherysCore;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

@Deprecated
public class TransactionlessHibernateRepository<T extends Identifiable<ID>, ID extends Serializable> implements Repository<T, ID> {

    protected Hashtable<ID, T> cache = new Hashtable<>();

    protected SessionFactory sessionFactory;

    protected Class<T> persistable;

    public TransactionlessHibernateRepository(Class<T> persistable) {
        this.persistable = persistable;

        EntityManagerFactory entityManagerFactory = AtherysCore.getEntityManagerFactory();

        if (entityManagerFactory instanceof SessionFactory) {
            this.sessionFactory = (SessionFactory) entityManagerFactory;
        } else {
            throw new IllegalStateException("JPA implementation is not Hibernate ( EMF is not instance of SessionFactory ).");
        }
    }

    @Override
    public Optional<T> findById(ID id) {
        T result;

        T cachedEntity = cache.get(id);

        if (cachedEntity == null) {
            result = sessionFactory.getCurrentSession().find(persistable, id);
        } else {
            result = cachedEntity;
        }

        return Optional.ofNullable(result);
    }

    @Override
    public void saveOne(T entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
        cache.put(entity.getId(), entity);
    }

    @Override
    public void saveAll(Collection<T> entities) {
        entities.forEach(this::saveOne);
    }

    @Override
    public void deleteOne(T entity) {
        sessionFactory.getCurrentSession().delete(entity);
        cache.remove(entity.getId());
    }

    @Override
    public void deleteAll(Collection<T> entities) {
        entities.forEach(this::deleteOne);
    }

    @Override
    public CompletableFuture<Void> saveOneAsync(T entity) {
        return CompletableFuture.runAsync(() -> saveOne(entity));
    }

    @Override
    public CompletableFuture<Void> saveAllAsync(Collection<T> entities) {
        return CompletableFuture.runAsync(() -> saveAll(entities));
    }

    @Override
    public CompletableFuture<Void> deleteOneAsync(T entity) {
        return CompletableFuture.runAsync(() -> deleteOne(entity));
    }

    @Override
    public CompletableFuture<Void> deleteAllAsync(Collection<T> entities) {
        return CompletableFuture.runAsync(() -> deleteAll(entities));
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
        saveAll(cache.values());
    }
}
