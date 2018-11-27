package com.atherys.core.db;

import com.atherys.core.AtherysCore;
import org.slf4j.Logger;
import org.spongepowered.api.scheduler.Task;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

public abstract class AtherysRepository<T, ID> {

    private Logger logger;

    private Class<T> persistable;

    private EntityManager entityManager;

    protected Map<ID, T> cache = new HashMap<>();

    protected AtherysRepository(Class<T> persistable, Logger logger) {
        this.entityManager = AtherysCore.getEntityManagerFactory().createEntityManager();
        this.persistable = persistable;
        this.logger = logger;
    }

    private void transactionOf(Consumer<EntityManager> query) {
        Task.builder().async().execute(() -> {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            query.accept(entityManager);
            transaction.commit();
        }).submit(AtherysCore.class);
    }

    protected CriteriaBuilder getQueryBuilder() {
        return entityManager.getCriteriaBuilder();
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

    public Optional<T> findById(ID id) {
        return Optional.ofNullable(cache.getOrDefault(id, entityManager.find(persistable, id)));
    }

    public void saveOne(ID id, T entity) {
        transactionOf(em -> em.persist(entity));
        cache.put(id, entity);
    }

    public void saveAll(Map<ID,T> entities) {
        transactionOf(em -> entities.forEach(this::saveOne));
    }

    public void deleteOne(ID id, T entity) {
        transactionOf(em -> em.remove(entity));
        cache.remove(id);
    }

    public void deleteAll(Map<ID,T> entities) {
        transactionOf(em -> entities.forEach(this::deleteOne));
    }

}
