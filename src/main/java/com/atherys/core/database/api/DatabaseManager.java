package com.atherys.core.database.api;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

/**
 * A database manager interface following the CRUD pattern.
 *
 * @param <T> The {@link DBObject} to be managed.
 */
public interface DatabaseManager<T extends DBObject> {

    /**
     * Save the provided {@link DBObject} to the database
     *
     * @param object The object to be saved
     */
    void save(T object);

    /**
     * Retrieve a {@link DBObject} from the database using the given UUID
     *
     * @param uuid the UUID to be searched for
     * @return An optional which could contain the object searched for.
     */
    Optional<T> get(UUID uuid);

    /**
     * Update the {@link DBObject} in the database.
     *
     * @param object The object to be updated
     */
    void update(T object);

    /**
     * Remove the {@link DBObject} from the database. Implementations should do this purely based on
     * the object's UUID, but this is not mandatory.
     *
     * @param object The object to be removed.
     */
    void remove(T object);

    /**
     * Save all provided {@link DBObject} to the database. See: {@link #save(DBObject)}
     *
     * @param objects The objects to be saved
     */
    void saveAll(Collection<T> objects);

    /**
     * If this DatabaseManager uses some sort of caching, call this method immediately after
     * instantiation so as to cache all {@link DBObject}s stored in the database.
     */
    void loadAll();

    /**
     * Update all provided {@link DBObject}s. See: {@link #update(DBObject)}
     *
     * @param objects the objects to be updated
     */
    void updateAll(Collection<T> objects);

    /**
     * Remove all provided {@link DBObject}s from the database. See: {@link #remove(DBObject)}
     *
     * @param objects the objects to be removed.
     */
    void removeAll(Collection<T> objects);
}
