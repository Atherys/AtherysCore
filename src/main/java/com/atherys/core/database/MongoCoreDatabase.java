package com.atherys.core.database;

import com.atherys.core.AtherysCore;
import com.atherys.core.database.mongo.MorphiaDatabase;

public class MongoCoreDatabase extends MorphiaDatabase {

    private static MongoCoreDatabase instance = new MongoCoreDatabase();

    private MongoCoreDatabase() {
        super(AtherysCore.getConfig().DATABASE, "com.atherys.core");
    }

    public static MongoCoreDatabase getInstance() {
        return instance;
    }

}
