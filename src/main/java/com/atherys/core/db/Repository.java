package com.atherys.core.db;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

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

    <R> void querySingle(String jpql, Class<R> result, Consumer<Optional<R>> resultConsumer);

    <R> void queryMultiple(String jpql, Class<R> result, Consumer<Collection<R>> resultConsumer);

    default <R> CompletableFuture<Void> querySingleAsync(String jpql, Class<R> result, Consumer<Optional<R>> resultConsumer) {
        return CompletableFuture.runAsync(() -> querySingle(jpql, result, resultConsumer));
    }

    default <R> CompletableFuture<Void> queryMultipleAsync(String jpql, Class<R> result, Consumer<Collection<R>> resultConsumer) {
        return CompletableFuture.runAsync(() -> queryMultiple(jpql, result, resultConsumer));
    }
}
