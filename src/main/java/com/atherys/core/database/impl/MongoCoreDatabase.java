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
        MongoCredential credential = MongoCredential.createCredential( AtherysCore.getConfig().mongoUsername, AtherysCore.getConfig().mongoUserDB, AtherysCore.getConfig().mongoPassword.toCharArray() );
        client = new MongoClient( new ServerAddress( AtherysCore.getConfig().mongoHost, AtherysCore.getConfig().mongoPort ), Arrays.asList(credential) );
        db = client.getDatabase( AtherysCore.getConfig().mongoDatabase );
    }

    @Override
    public MongoDatabase getDatabase() {
        return db;
    }

    public static MongoCoreDatabase getInstance() {
        return instance;
    }

}
