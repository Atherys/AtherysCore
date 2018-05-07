package com.atherys.core.database;

import com.atherys.core.AtherysCore;
import com.atherys.core.database.mongo.AbstractMongoDatabase;

public class MongoCoreDatabase extends AbstractMongoDatabase {

  private static MongoCoreDatabase instance = new MongoCoreDatabase();

  private MongoCoreDatabase() {
    super(AtherysCore.getConfig().DATABASE);
  }

  public static MongoCoreDatabase getInstance() {
    return instance;
  }

}
