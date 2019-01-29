package com.atherys.core.db;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface Repository<T extends Identifiable<ID>, ID extends Serializable> {

    /**
     * Find an entity by it's id
     *
     * @param id The id to look for
     * @return An optional containing the entity. Empty if not found.
     */
    Optional<T> findById(ID id);

    /**
     * Insert or Update an entity to the database
     *
     * @param entity The entity to persist
     */
    void saveOne(T entity);

    /**
     * Insert or Update multiple entities to the database
     *
     * @param entities The entities to persist
     */
    void saveAll(Collection<T> entities);

    /**
     * Delete an entity from the database
     *
     * @param entity The entity to delete
     */
    void deleteOne(T entity);

    /**
     * Delete multiple entities from the database
     *
     * @param entities The entities to delete
     */
    void deleteAll(Collection<T> entities);

    /**
     * Retrieve a CriteriaBuilder object from the repository
     *
     * @return A CriteriaBuilder instance
     */
    CriteriaBuilder getCriteriaBuilder();

    /**
     * Create a TypedQuery from a JPQL string, and the class of the expected result
     *
     * @param jpql   The JPQL String
     * @param result The expected result class
     * @return A TypedQuery object
     */
    <R> TypedQuery<R> createQuery(String jpql, Class<R> result);

    /**
     * Create a TypedQuery from a CriteriaQuery, and the class of the expected result
     *
     * @param criteriaQuery The CriteriaQuery
     * @return A TypedQuery object
     */
    <R> TypedQuery<R> createQuery(CriteriaQuery<R> criteriaQuery);

    /**
     * Query the database for a single result
     *
     * @param query The query
     * @return An optional containing the result, or empty if not found
     */
    <R> Optional<R> querySingle(TypedQuery<R> query);

    /**
     * Query the database for multiple results
     *
     * @param query The query
     * @return A collection containing the results, or empty if not found
     */
    <R> Collection<R> queryMultiple(TypedQuery<R> query);

    /**
     * Query the database for a single result asynchronously
     *
     * @param query The query
     * @return A CompletableFuture containing an Optional containing the result, or empty if not found
     */
    <R> CompletableFuture<Optional<R>> querySingleAsync(TypedQuery<R> query);

    /**
     * Query the database for multiple results asynchronously
     *
     * @param query The query
     * @return A CompletableFuture containing a collection containing the results, or empty if not found
     */
    <R> CompletableFuture<Collection<R>> queryMultipleAsync(TypedQuery<R> query);
}
