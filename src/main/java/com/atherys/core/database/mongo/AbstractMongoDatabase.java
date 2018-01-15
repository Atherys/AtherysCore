package com.atherys.core.database.mongo;

import com.atherys.core.database.api.Database;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

import java.util.Arrays;

public abstract class AbstractMongoDatabase implements Database<MongoDatabase> {

    private MongoDatabase db;

    protected AbstractMongoDatabase(MongoDatabaseConfig config ) {
        MongoCredential credential = MongoCredential.createCredential( config.USERNAME, config.USER_DB, config.PASSWORD.toCharArray() );
        MongoClient client = new MongoClient(new ServerAddress(config.HOST, config.PORT), Arrays.asList(credential));

        db = client.getDatabase( config.NAME );
    }

    @Override
    public MongoDatabase getDatabase() {
        return db;
    }
}
