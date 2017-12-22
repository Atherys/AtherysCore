package com.atherys.core.database.api;

import java.util.Collection;

public interface DatabaseManager<T extends DBObject> {

    void saveOne ( T object );

    void saveAll ( Collection<T> objects );

    void loadAll ();

    void removeOne ( T object );
}
