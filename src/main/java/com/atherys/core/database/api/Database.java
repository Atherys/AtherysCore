package com.atherys.core.database.api;

/**
 * An interface designed to wrap around database objects.
 * @param <T> The database object
 */
public interface Database<T> {

    /**
     * Retrieve the database object
     * @return the database object
     */
    T getDatabase();

}
