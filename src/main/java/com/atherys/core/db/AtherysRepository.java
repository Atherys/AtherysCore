package com.atherys.core.db;

import com.atherys.core.AtherysCore;
import org.slf4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.stream.Stream;

public abstract class AtherysRepository<T extends Identifiable<ID>, ID> {

    protected Logger logger;

    protected Class<T> persistable;

    protected EntityManager entityManager;

    protected Map<ID, T> cache = new HashMap<>();

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

    /**
     * Used to retrieve the {@link CriteriaBuilder} from the {@link javax.persistence.EntityManager}.
     *
     * @return the CriteriaBuilder instance
     */
    protected CriteriaBuilder getCriteriaBuilder() {
        return entityManager.getCriteriaBuilder();
    }

    /**
     * Creates a new JPQL query
     *
     * @param jpql The JPQL String
     * @param result The resulting type's class
     * @param <R> The resulting type
     * @return The query
     */
    protected <R> TypedQuery<R> createQuery(String jpql, Class<R> result) {
        return entityManager.createQuery(jpql, result);
    }

    /**
     * Creates a new {@link TypedQuery} from the provided {@link CriteriaQuery}
     *
     * @param criteriaQuery the criteria query
     * @param <R> the type of the result
     * @return the query
     */
    protected <R> TypedQuery<R> createQuery(CriteriaQuery<R> criteriaQuery) {
        return entityManager.createQuery(criteriaQuery);
    }

    /**
     * Query the database, expecting a single result
     *
     * @param query The query
     * @param <R> the type of the result
     * @return An optional containing the result. Empty if an exception is thrown, or if the result is empty.
     */
    protected <R> Optional<R> querySingle(TypedQuery<R> query) {
        try {
            return Optional.ofNullable(query.getSingleResult());
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    /**
     * Query the database, expecting multiple results
     *
     * @param query the query
     * @param <R> The type of the result
     * @return A list of the resulting entities, or an empty list if an exception was thrown or the query returned no results.
     */
    protected <R> List<R> queryMultiple(TypedQuery<R> query) {
        try {
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Like {@link #querySingle(TypedQuery)}, but async
     *
     * @param query The query
     * @param <R> the type of the result
     * @return An optional containing the result. Empty if an exception is thrown, or if the result is empty.
     */
    protected <R> CompletableFuture<Optional<R>> asyncQuerySingle(TypedQuery<R> query) {
        return CompletableFuture.supplyAsync(() -> querySingle(query));
    }

    /**
     * Like {@link #queryMultiple(TypedQuery)}, but async
     *
     * @param query the query
     * @param <R> The type of the result
     * @return A list of the resulting entities, or an empty list if an exception was thrown or the query returned no results.
     */
    protected <R> CompletableFuture<List<R>> asyncQueryMultiple(TypedQuery<R> query) {
        return CompletableFuture.supplyAsync(() -> queryMultiple(query));
    }

    /**
     * Find an entity by it's id. First this method will look through the cache, and if a match is found there, will return it.
     * Otherwise, a query will be sent to the database looking for an entity matching that id.
     *
     * @param id The id of the entity
     * @return An optional containing the entity. empty if could not be found.
     */
    public Optional<T> findById(ID id) {
        T cacheResult = cache.get(id);
        if (cacheResult == null) {

            T dbResult = entityManager.find(persistable, id);
            if (dbResult == null) {
                return Optional.empty();
            } else {
                entityManager.detach(dbResult);
                cache.put(dbResult.getId(), dbResult);
                return Optional.of(dbResult);
            }
        } else return Optional.of(cacheResult);
    }

    /**
     * Saves a single entity to the cache and the database. Uses a transaction.
     *
     * @param entity the entity to be saved
     */
    public void saveOne(T entity) {
        transactionOf(em -> em.persist(entity));
        cache.put(entity.getId(), entity);
    }

    /**
     * Saves all entities in the collection to the cache and the database, creating a transaction for each one.
     *
     * @param entities the entities to be saved
     */
    public void saveAll(Collection<T> entities) {
        entities.forEach(this::saveOne);
    }

    /**
     * Deletes a single entity from the cache and the database. Uses a transaction.
     *
     * @param entity The entity to be deleted
     */
    public void deleteOne(T entity) {
        transactionOf(em -> em.remove(entity));
        cache.remove(entity.getId());
    }

    /**
     * Deletes all entities in the collection from the cache and the database, creating a transaction for each one.
     *
     * @param entities The entities to be deleted
     */
    public void deleteAll(Collection<T> entities) {
        entities.forEach(this::deleteOne);
    }

    /**
     * Used for querying the cache in parallel
     *
     * @return A parallel stream of the cache's contents
     */
    public Stream<T> cacheParallelStream() {
        return cache.values().parallelStream();
    }

    /**
     * Used for querying the cache
     *
     * @return A stream of the cache's contents
     */
    public Stream<T> cacheStream() {
        return cache.values().stream();
    }

    /**
     * Loads all entities of the type from the database into the cache, detaching each
     */
    public void cacheAll() {
        CriteriaBuilder builder = getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(persistable);
        Root<T> variableRoot = query.from(persistable);
        query.select(variableRoot);

        asyncQueryMultiple(createQuery(query)).thenAccept(entities -> {
            entities.forEach(entity -> {
                entityManager.detach(entity);
                cache.put(entity.getId(), entity);
            });
        });
    }

    /**
     * Saves all entities currently in the cache to the database
     */
    public void flushCache() {
        saveAll(cache.values());
    }
}
