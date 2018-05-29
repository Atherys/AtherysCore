package com.atherys.core.database.mongo;

import com.atherys.core.database.api.Database;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * An abstract implementation of {@link Database} using the MongoDB Java Driver
 */
@Deprecated
public abstract class AbstractMongoDatabase implements Database<MongoDatabase> {

    private MongoDatabase db;

    private Map<Class<?>,MongoTypeAdapter> typeAdapters = new HashMap<>();

    protected AbstractMongoDatabase(MongoDatabaseConfig config) {
        MongoCredential credential = MongoCredential
                .createCredential(config.USERNAME, config.USER_DB, config.PASSWORD.toCharArray());
        MongoClient client = new MongoClient(new ServerAddress(config.HOST, config.PORT),
                Arrays.asList(credential));

        db = client.getDatabase(config.NAME);
    }

    public Optional<MongoTypeAdapter> getAdapter(Class<?> clazz) {
        return Optional.ofNullable(typeAdapters.get(clazz));
    }

    public void registerAdapter(MongoTypeAdapter adapter) {
        typeAdapters.put(adapter.getApplicableClass(), adapter);
    }

    public void removeAdapter(Class<?> clazz) {
        typeAdapters.remove(clazz);
    }

    @Override
    public MongoDatabase getDatabase() {
        return db;
    }
}
