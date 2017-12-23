package com.atherys.core.database.impl;

import com.atherys.core.AtherysCore;
import com.atherys.core.database.api.Database;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

import java.util.Arrays;

public class MongoCoreDatabase implements Database<MongoDatabase> {

    private static MongoCoreDatabase instance = new MongoCoreDatabase();

    private MongoClient client;
    private MongoDatabase db;

    private MongoCoreDatabase() {
        MongoCredential credential = MongoCredential.createCredential( AtherysCore.getConfig().DATABASE.USERNAME, AtherysCore.getConfig().DATABASE.USER_DB, AtherysCore.getConfig().DATABASE.PASSWORD.toCharArray() );
        client = new MongoClient( new ServerAddress( AtherysCore.getConfig().DATABASE.HOST, AtherysCore.getConfig().DATABASE.PORT ), Arrays.asList(credential) );
        db = client.getDatabase( AtherysCore.getConfig().DATABASE.NAME );
    }

    @Override
    public MongoDatabase getDatabase() {
        return db;
    }

    public static MongoCoreDatabase getInstance() {
        return instance;
    }

}
