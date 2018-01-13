package com.atherys.core.database.api;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface DatabaseManager<T extends DBObject> {

    void save ( T object );

    Optional<T> get ( UUID uuid );

    void update ( T object );

    void remove ( T object );

    void saveAll ( Collection<T> objects );

    void loadAll ();

    void updateAll ( Collection<T> objects );

    void removeAll ( Collection<T> objects );
}
