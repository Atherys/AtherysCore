package com.atherys.core.db;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class HibernateRepository<T extends Identifiable<ID>, ID extends Serializable> implements Repository<T, ID> {
    @Override
    public Optional<T> findById(ID id) {
        // TODO
        return Optional.empty();
    }

    @Override
    public void saveOne(T entity) {
        // TODO
    }

    @Override
    public void saveAll(Collection<T> entities) {
        // TODO
    }

    @Override
    public void deleteOne(T entity) {
        // TODO
    }

    @Override
    public void deleteAll(Collection<T> entities) {
        // TODO
    }

    @Override
    public CriteriaBuilder getCriteriaBuilder() {
        // TODO
        return null;
    }

    @Override
    public <R> TypedQuery<R> createQuery(String jpql, Class<R> result) {
        // TODO
        return null;
    }

    @Override
    public <R> TypedQuery<R> createQuery(CriteriaQuery<R> criteriaQuery) {
        // TODO
        return null;
    }

    @Override
    public <R> Optional<R> querySingle(TypedQuery<R> query) {
        // TODO
        return Optional.empty();
    }

    @Override
    public <R> Collection<R> queryMultiple(TypedQuery<R> query) {
        // TODO
        return null;
    }

    @Override
    public <R> CompletableFuture<Optional<R>> querySingleAsync(TypedQuery<R> query) {
        // TODO
        return null;
    }

    @Override
    public <R> CompletableFuture<Collection<R>> queryMultipleAsync(TypedQuery<R> query) {
        // TODO
        return null;
    }
}
