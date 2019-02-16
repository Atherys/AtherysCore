package com.atherys.core.db;

import com.atherys.core.AtherysCore;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

/**
 * !! Not Yet Implemented !!<br>
 * This is an implementation-agnostic Repository
 */
@Deprecated
public class JpaRepository<T extends Identifiable<ID>, ID extends Serializable> implements Repository<T, ID> {

    private EntityManagerFactory emf;

    private Class<T> persistable;

    protected JpaRepository(Class<T> persistable) {
        this.persistable = persistable;
        this.emf = AtherysCore.getEntityManagerFactory();
    }

    @Override
    public Optional<T> findById(ID id) {
        T result;

        EntityManager em = emf.createEntityManager();
        result = em.find(persistable, id);
        em.close();

        return Optional.ofNullable(result);
    }

    @Override
    public void saveOne(T entity) {
        EntityManager em = emf.createEntityManager();
        em.persist(entity);
        em.close();
    }

    @Override
    public void saveAll(Collection<T> entities) {
    }

    @Override
    public void deleteOne(T entity) {
        EntityManager em = emf.createEntityManager();
        em.remove(entity);
        em.close();
    }

    @Override
    public void deleteAll(Collection<T> entities) {

    }

    @Override
    public CompletableFuture<Void> saveOneAsync(T entity) {
        return null;
    }

    @Override
    public CompletableFuture<Void> saveAllAsync(Collection<T> entities) {
        return null;
    }

    @Override
    public CompletableFuture<Void> deleteOneAsync(T entity) {
        return null;
    }

    @Override
    public CompletableFuture<Void> deleteAllAsync(Collection<T> entities) {
        return null;
    }

    @Override
    public CriteriaBuilder getCriteriaBuilder() {
        return null;
    }

    @Override
    public void execute(String jpql, Consumer<Query> setParams) {

    }

    @Override
    public <R> void querySingle(String jpql, Class<R> result, Consumer<Query> setParams, Consumer<Optional<R>> resultConsumer) {

    }

    @Override
    public <R> void queryMultiple(String jpql, Class<R> result, Consumer<Query> setParams, Consumer<Collection<R>> resultConsumer) {

    }

    @Override
    public <R> void querySingle(CriteriaQuery<R> query, Consumer<Query> setParams, Consumer<Optional<R>> resultConsumer) {

    }

    @Override
    public <R> void queryMultiple(CriteriaQuery<R> query, Consumer<Query> setParams, Consumer<Collection<R>> resultConsumer) {

    }
}
