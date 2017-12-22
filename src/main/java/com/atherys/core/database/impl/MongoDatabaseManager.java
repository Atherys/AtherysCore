package com.atherys.core.database.impl;

import com.atherys.core.AtherysCore;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class MongoDatabaseManager<T extends DBObject> implements DatabaseManager<T> {

    protected abstract MongoCollection<Document> collection();

    protected abstract Document toDocument ( T object );

    public void saveOne ( T object ) {
        Bson update = new Document ( "$set", toDocument(object) );

        UpdateOptions options = new UpdateOptions().upsert(true);

        collection().updateOne(
                Filters.eq( "_id", object.getUUID().toString() ),
                update,
                options
        );
    }

    public abstract void saveAll();

    public void saveAll( Collection<T> objects ) {
        List<WriteModel<Document>> updates = new ArrayList<>();

        for ( T object : objects ) {
            updates.add(
                    new UpdateOneModel<>(
                            new Document( "uuid", object.getUUID() ),
                            new Document( "$set", toDocument(object)),
                            new UpdateOptions().upsert(true)
                    )
            );
        }

        if ( !updates.isEmpty() ) {
            BulkWriteResult bulkWriteResult = collection().bulkWrite(updates);


            if (bulkWriteResult.wasAcknowledged()) {
                int mods = bulkWriteResult.getModifiedCount();
                int inserts = bulkWriteResult.getInsertedCount();
                int deletes = bulkWriteResult.getDeletedCount();
                int matched = bulkWriteResult.getMatchedCount();

                AtherysCore.getInstance().getLogger().info(
                        "[MongoDB] Updated " + updates.size() + " " + this.getClass().getSimpleName().replace("Manager", "") + "(s), " +
                                "where " + mods + " were modified, "
                                + inserts + " were added, "
                                + deletes + " were removed, and "
                                + matched + " were matched."
                );
            }
        }
    }

    protected abstract boolean fromDocument(Document doc);

    public void loadAll() {
        int found = 0;
        int loaded = 0;

        try ( MongoCursor<Document> cursor = collection().find().iterator() ) {
            while (cursor.hasNext()) {
                if (fromDocument(cursor.next())) loaded++;
                found++;
            }
        }

        AtherysCore.getInstance().getLogger().info( "[MongoDB] " + this.getClass().getSimpleName() + " Loaded " + loaded + "/" + found );
    }

    public void removeOne ( T object ) {
        collection().deleteOne( new Document( "uuid", object.getUUID() ) );
    }

}
