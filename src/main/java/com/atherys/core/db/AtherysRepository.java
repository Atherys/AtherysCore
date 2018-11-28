package com.atherys.core.db;

import com.atherys.core.AtherysCore;
import org.slf4j.Logger;
import org.spongepowered.api.util.Identifiable;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public abstract class AtherysRepository<T extends Identifiable> {

    private Logger logger;

    private Class<T> persistable;

    private EntityManager entityManager;

    protected Map<UUID, T> cache = new HashMap<>();

    protected AtherysRepository(Class<T> persistable, Logger logger) {
        this.entityManager = AtherysCore.getEntityManagerFactory().createEntityManager();
        this.persistable = persistable;
        this.logger = logger;
    }

    private void transactionOf(Consumer<EntityManager> query) {
        CompletableFuture.runAsync(() -> {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            query.accept(entityManager);
            transaction.commit();
        });
    }

    protected CriteriaBuilder getCriteriaBuilder() {
        return entityManager.getCriteriaBuilder();
    }

    protected <R> TypedQuery<R> createQuery(String hql, Class<R> result) {
        return entityManager.createQuery(hql, result);
    }

    protected <R> TypedQuery<R> createQuery(CriteriaQuery<R> criteriaQuery) {
        return entityManager.createQuery(criteriaQuery);
    }

    protected <R> Optional<R> querySingle(TypedQuery<R> query) {
        try {
            return Optional.ofNullable( query.getSingleResult() );
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    protected <R> List<R> queryMultiple(TypedQuery<R> query) {
        try {
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    protected <R> CompletableFuture<Optional<R>> asyncQuerySingle(TypedQuery<R> query) {
        return CompletableFuture.supplyAsync(() -> querySingle(query));
    }

    protected <R> CompletableFuture<List<R>> asyncQueryMultiple(TypedQuery<R> query) {
        return CompletableFuture.supplyAsync(() -> queryMultiple(query));
    }

    public Optional<T> findById(UUID id) {
        return Optional.ofNullable(cache.getOrDefault(id, entityManager.find(persistable, id)));
    }

    public void saveOne(T entity) {
        transactionOf(em -> em.persist(entity));
        cache.put(entity.getUniqueId(), entity);
    }

    public void saveAll(Collection<T> entities) {
        transactionOf(em -> entities.forEach(this::saveOne));
    }

    public void deleteOne(T entity) {
        transactionOf(em -> em.remove(entity));
        cache.remove(entity.getUniqueId());
    }

    public void deleteAll(Collection<T> entities) {
        transactionOf(em -> entities.forEach(this::deleteOne));
    }

}
