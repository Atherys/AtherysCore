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
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

public class AtherysRepository<T,ID> {

    private Logger logger;

    private Class<T> persistable;

    private EntityManager entityManager;

    protected AtherysRepository(Class<T> persistable, Logger logger) {
        this.entityManager = AtherysCore.getEntityManagerFactory().createEntityManager();
        this.persistable = persistable;
        this.logger = logger;
    }

    protected void transactionOf(Consumer<EntityManager> query) {
        Task.builder().async().execute(() -> {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            query.accept(entityManager);
            transaction.commit();
        }).submit(AtherysCore.class);
    }

    public Optional<T> findById(ID id) {
        return Optional.ofNullable(entityManager.find(persistable, id));
    }

    public void saveOne(T entity) {
        transactionOf(em -> em.persist(entity));
    }

    public void saveAll(Collection<T> entities) {
        transactionOf(em -> entities.forEach(em::persist));
    }

    public void deleteOne(T entity) {
        transactionOf(em -> em.remove(entity));
    }

    public void deleteAll(Collection<T> entities) {
        transactionOf(em -> entities.forEach(em::remove));
    }

    protected Query createQuery(String hql) {
        return entityManager.createQuery(hql);
    }

    protected CriteriaBuilder getQueryBuilder() {
        return entityManager.getCriteriaBuilder();
    }

    protected <R> TypedQuery<R> query(CriteriaQuery<R> criteriaQuery) {
        return entityManager.createQuery(criteriaQuery);
    }

    protected <R> void transactionQuery(CriteriaQuery<R> criteriaQuery) {
        transactionOf(em -> em.createQuery(criteriaQuery).executeUpdate());
    }

}
