package com.atherys.core.db;

import com.atherys.core.AtherysCore;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;
import java.util.Optional;
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
    public <R> void querySingle(String jpql, Class<R> result, Consumer<Optional<R>> resultConsumer) {
        resultConsumer.accept(Optional.ofNullable(sessionFactory.getCurrentSession().createQuery(jpql, result).getSingleResult()));
    }

    @Override
    public <R> void queryMultiple(String jpql, Class<R> result, Consumer<Collection<R>> resultConsumer) {
        resultConsumer.accept(sessionFactory.getCurrentSession().createQuery(jpql, result).getResultList());

    }

    @Override
    public <R> void querySingle(CriteriaQuery<R> query, Consumer<Optional<R>> resultConsumer) {
        resultConsumer.accept(Optional.ofNullable(sessionFactory.getCurrentSession().createQuery(query).getSingleResult()));
    }

    @Override
    public <R> void queryMultiple(CriteriaQuery<R> query, Consumer<Collection<R>> resultConsumer) {
        resultConsumer.accept(sessionFactory.getCurrentSession().createQuery(query).getResultList());
    }

    protected Map<ID, T> getCache() {
        return cache;
    }

    public void cacheAll() {
        CriteriaBuilder builder = getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(persistable);
        Root<T> variableRoot = query.from(persistable);

        query.select(variableRoot);

        queryMultiple(query, entities -> entities.forEach(entity -> {
            cache.put(entity.getId(), entity);
        }));
    }

    public void flushCache() {
        saveAll(cache.values());
    }
}
