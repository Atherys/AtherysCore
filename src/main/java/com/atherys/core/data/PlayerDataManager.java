package com.atherys.core.data;

import com.atherys.core.data.player.PlayerData;
import com.atherys.core.database.impl.MongoCoreDatabase;
import com.atherys.core.database.impl.MongoDatabaseManager;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.*;

public final class PlayerDataManager extends MongoDatabaseManager<PlayerData> {

    private static final PlayerDataManager instance = new PlayerDataManager();

    private Map<Class,DataAdapter> adapters = new HashMap<>(); // TODO: Figure out a way to tell if MongoDB can store the given object. If not, look for adapter. If no adapter is found, throw exception.

    private Map<UUID,PlayerData> playerData = new HashMap<>();

    private PlayerDataManager() {}

    public Optional<PlayerData> get ( UUID uuid ) {
        return Optional.ofNullable( playerData.get(uuid) );
    }

    @Override
    protected MongoCollection<Document> collection() {
        return MongoCoreDatabase.getInstance().getDatabase().getCollection("player-data");
    }

    @Override
    protected Document toDocument(PlayerData object) {
        Document doc = new Document( "uuid", object.getUUID() );

        Document dataDoc = new Document();
        object.getData().forEach ( dataDoc::append );

        doc.append( "data", dataDoc );

        return doc;
    }

    @Override
    public void saveAll() {
        saveAll( playerData.values() );
    }

    @Override
    protected boolean fromDocument(Document doc) {
        return false;
    }

    public static PlayerDataManager getInstance() {
        return instance;
    }
}
