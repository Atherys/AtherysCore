package com.atherys.core.database.mongo;

import com.atherys.core.database.api.DBObject;
import com.atherys.core.database.api.DatabaseManager;
import org.slf4j.Logger;

import java.util.*;

public class MorphiaDatabaseManager<T extends DBObject> implements DatabaseManager<T> {

    private Class<T> entityClass;
    private MorphiaDatabase morphia;
    private Logger logger;

    private Map<UUID,T> cache = new HashMap<>();

    public MorphiaDatabaseManager(MorphiaDatabase morphia, Logger logger, Class<T> entityClass) {
        this.morphia = morphia;
        this.logger = logger;
        this.entityClass = entityClass;
    }

    @Override
    public void save(T object) {
        morphia.getDatabase().save(object);
    }

    @Override
    public Optional<T> get(UUID uuid) {
        return Optional.ofNullable(morphia.getDatabase().get(entityClass, uuid));
    }

    @Override
    public void update(T object) {
        morphia.getDatabase().save(object);
    }

    @Override
    public void remove(T object) {
        getCache().remove(object.getUUID());
        morphia.getDatabase().delete(object);
    }

    @Override
    public void saveAll(Collection<T> objects) {
        morphia.getDatabase().save(objects);
    }

    @Override
    public void loadAll() {
        morphia.getDatabase().createQuery(entityClass).asList().forEach(entity -> cache.put(entity.getUUID(), entity));
    }

    @Override
    public void updateAll(Collection<T> objects) {
        morphia.getDatabase().save(objects);
    }

    @Override
    public void removeAll(Collection<T> objects) {
        morphia.getDatabase().delete(objects);
    }

    protected Map<UUID,T> getCache() {
        return cache;
    }
}
