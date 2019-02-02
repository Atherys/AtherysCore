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
        Session session = sessionFactory.openSession();
        T result = session.find(persistable, id);
        session.close();

        return Optional.ofNullable(result);
    }

    @Override
    public void saveOne(T entity) {
        transactionOf(session -> {
            session.saveOrUpdate(entity);
        });
    }

    @Override
    public void saveAll(Collection<T> entities) {
        transactionOf(session -> entities.forEach((session::saveOrUpdate)));
    }

    @Override
    public void deleteOne(T entity) {
        transactionOf(session -> session.delete(entity));
    }

    @Override
    public void deleteAll(Collection<T> entities) {
        transactionOf(session -> entities.forEach(session::delete));
    }

    @Override
    public CompletableFuture<Void> saveOneAsync(T entity) {
        return asyncTransactionOf(session -> session.saveOrUpdate(entity));
    }

    @Override
    public CompletableFuture<Void> saveAllAsync(Collection<T> entities) {
        return asyncTransactionOf(session -> entities.forEach((session::saveOrUpdate)));
    }

    @Override
    public CompletableFuture<Void> deleteOneAsync(T entity) {
        return asyncTransactionOf(session -> session.delete(entity));
    }

    @Override
    public CompletableFuture<Void> deleteAllAsync(Collection<T> entities) {
        return asyncTransactionOf(session -> entities.forEach(session::delete));
    }

    @Override
    public CriteriaBuilder getCriteriaBuilder() {
        return sessionFactory.getCriteriaBuilder();
    }

    @Override
    public <R> void querySingle(String jpql, Class<R> result, Consumer<Optional<R>> resultConsumer) {
        try (Session session = sessionFactory.openSession()) {
            Query<R> query = session.createQuery(jpql, result);
            R r = query.getSingleResult();
            resultConsumer.accept(Optional.ofNullable(r));
        }
    }

    @Override
    public <R> void queryMultiple(String jpql, Class<R> result, Consumer<Collection<R>> resultConsumer) {
        try (Session session = sessionFactory.openSession()) {
            Query<R> query = session.createQuery(jpql, result);
            List<R> r = query.getResultList();
            resultConsumer.accept(r);
        }
    }

    @Override
    public <R> void querySingle(CriteriaQuery<R> query, Consumer<Optional<R>> resultConsumer) {
        try (Session session = sessionFactory.openSession()) {
            Query<R> q = session.createQuery(query);
            R r = q.getSingleResult();
            resultConsumer.accept(Optional.ofNullable(r));
        }
    }

    @Override
    public <R> void queryMultiple(CriteriaQuery<R> query, Consumer<Collection<R>> resultConsumer) {
        try (Session session = sessionFactory.openSession()) {
            Query<R> q = session.createQuery(query);
            List<R> r = q.getResultList();
            resultConsumer.accept(r);
        }
    }
}
