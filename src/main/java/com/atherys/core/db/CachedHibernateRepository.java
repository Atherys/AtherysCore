package com.atherys.core.db;

import com.atherys.core.db.cache.Cache;
import com.atherys.core.db.cache.SimpleCache;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class CachedHibernateRepository<T extends Identifiable<ID>, ID extends Serializable> extends HibernateRepository<T, ID> {

    protected Cache<T, ID> cache = new SimpleCache<>();

    public CachedHibernateRepository(Class<T> persistable) {
        super(persistable);
    }

    @Override
    public Optional<T> findById(ID id) {
        return cache.getById(id);
    }

    public Optional<T> findOne(Predicate<T> condition) {
        return cache.findOne(condition);
    }

    public Collection<T> findAll(Predicate<T> condition) {
        return cache.findAll(condition);
    }

    @Override
    public void saveOne(T entity) {
        cache.add(entity);
        super.saveOne(entity);
    }

    @Override
    public void saveAll(Collection<T> entities) {
        cache.addAll(entities);
        super.saveAll(entities);
    }

    @Override
    public void deleteOne(T entity) {
        cache.remove(entity);
        super.deleteOne(entity);
    }

    @Override
    public void deleteAll(Collection<T> entities) {
        cache.removeAll(entities);
        super.deleteAll(entities);
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
    public <R> void querySingle(String jpql, Class<R> result, Consumer<Query> setParams, Consumer<Optional<R>> resultConsumer) {
        throw new UnsupportedOperationException("Cannot query cached repositories. Use findAll, findOne, or extend a non-cached repository instead.");
    }

    @Override
    public <R> void queryMultiple(String jpql, Class<R> result, Consumer<Query> setParams, Consumer<Collection<R>> resultConsumer) {
        throw new UnsupportedOperationException("Cannot query cached repositories. Use findAll, findOne, or extend a non-cached repository instead.");
    }

    @Override
    public <R> void querySingle(CriteriaQuery<R> query, Consumer<Query> setParams, Consumer<Optional<R>> resultConsumer) {
        throw new UnsupportedOperationException("Cannot query cached repositories. Use findAll, findOne, or extend a non-cached repository instead.");
    }

    @Override
    public <R> void queryMultiple(CriteriaQuery<R> query, Consumer<Query> setParams, Consumer<Collection<R>> resultConsumer) {
        throw new UnsupportedOperationException("Cannot query cached repositories. Use findAll, findOne, or extend a non-cached repository instead.");
    }

    public void initCache() {
        CriteriaBuilder builder = getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(persistable);
        Root<T> variableRoot = query.from(persistable);

        query.select(variableRoot);

        super.queryMultiple(query, (q) -> {
        }, entities -> entities.forEach(entity -> cache.set(entity.getId(), entity)));
    }

    public void flushCache() {
        super.saveAll(cache.getAll());
    }
}
