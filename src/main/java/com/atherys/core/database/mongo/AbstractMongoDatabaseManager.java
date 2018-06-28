package com.atherys.core.database.mongo;

import com.atherys.core.database.api.DBObject;
import com.atherys.core.database.api.DatabaseManager;
import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOneModel;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.WriteModel;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.slf4j.Logger;

import java.util.*;

/**
 * An abstract implementation of {@link DatabaseManager} using the MongoDB Java Driver.
 *
 * @param <T> the type of object which will be managed
 */
public abstract class AbstractMongoDatabaseManager<T extends DBObject> implements DatabaseManager<T> {

    private final Logger logger;

    private String collection;
    private AbstractMongoDatabase mongo;

    private Class<T> clazz;
    private Map<UUID, T> cache = new HashMap<>();

    protected AbstractMongoDatabaseManager(Logger logger, AbstractMongoDatabase mongoDatabase, Class<T> clazz) {
        this.collection = clazz.getSimpleName();
        this.mongo = mongoDatabase;
        this.logger = logger;
        this.clazz = clazz;
    }

    protected Map<UUID, T> getCache() {
        return cache;
    }

    protected MongoCollection<Document> getCollection() {
        return mongo.getDatabase().getCollection(collection);
    }

    @Override
    public void save(T object) {
        this.getCache().put(object.getUUID(), object);
        toDocument(object).ifPresent(doc -> {
            Bson update = new Document("$set", doc);
            UpdateOptions options = new UpdateOptions().upsert(true);

            getCollection().updateOne(
                    Filters.eq("_id", object.getUUID().toString()),
                    update,
                    options
            );
        });
    }

    @Override
    public Optional<T> get(UUID uuid) {
        return Optional.ofNullable(this.getCache().get(uuid));
    }

    @Override
    public void update(T object) {
        save(object);
    }

    @Override
    public void remove(T object) {
        getCollection().deleteOne(new Document("_id", object.getUUID()));
    }

    @Override
    public void saveAll(Collection<T> objects) {
        List<WriteModel<Document>> updates = new ArrayList<>();

        for (T object : objects) {
            Optional<Document> doc = toDocument(object);
            if (!doc.isPresent()) {
                continue;
            }

            updates.add(
                    new UpdateOneModel<>(
                            new Document("uuid", object.getUUID()),
                            new Document("$set", doc.get()),
                            new UpdateOptions().upsert(true)
                    )
            );
        }

        if (!updates.isEmpty()) {
            BulkWriteResult bulkWriteResult = getCollection().bulkWrite(updates);

            if (bulkWriteResult.wasAcknowledged()) {
                int mods = bulkWriteResult.getModifiedCount();
                int inserts = bulkWriteResult.getInsertedCount();
                int deletes = bulkWriteResult.getDeletedCount();
                int matched = bulkWriteResult.getMatchedCount();

                logger.info(
                        "[MongoDB] " + this.getClass().getSimpleName() + " updated " + updates.size()
                                + " objects, " +
                                "where " + mods + " were modified, "
                                + inserts + " were added, "
                                + deletes + " were removed, and "
                                + matched + " were matched."
                );
            }
        }
    }

    @Override
    public void loadAll() {
        int found = 0;
        int loaded = 0;

        try (MongoCursor<Document> cursor = getCollection().find().iterator()) {
            while (cursor.hasNext()) {
                Optional<T> object = fromDocument(cursor.next());
                if (object.isPresent()) {
                    this.getCache().put(object.get().getUUID(), object.get());
                    loaded++;
                }
                found++;
            }
        }

        logger.info("[MongoDB] " + this.getClass().getSimpleName() + " Loaded " + loaded + "/" + found);
    }

    @Override
    public void updateAll(Collection<T> objects) {
        saveAll(objects);
    }

    @Override
    public void removeAll(Collection<T> objects) {
        objects.forEach(this::remove);
    }

    protected Optional<MongoTypeAdapter> getAdapter() {
        return mongo.getAdapter(clazz);
    }

    @SuppressWarnings("unchecked")
    protected Optional<Document> toDocument(T object) {
        return getAdapter().flatMap(ta -> ta.toDocument(object));
    }

    @SuppressWarnings("unchecked")
    protected Optional<T> fromDocument(Document doc) {
        return getAdapter().flatMap(ta -> ta.fromDocument(doc));
    }
}
