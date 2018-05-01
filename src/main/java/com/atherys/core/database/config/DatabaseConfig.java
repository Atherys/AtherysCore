package com.atherys.core.database.config;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class DatabaseConfig {

    @Setting(value = "type", comment = "The type of database that will be used")
    public DatabaseType TYPE = DatabaseTypes.MONGODB;

    @Setting( value = "host", comment = "The host ip address of the MongoDB Database." )
    public String HOST = "localhost";

    @Setting( value = "port", comment = "The port on which the MongoDB Database is running." )
    public int PORT = 27017;

    @Setting( value = "name", comment = "The name of the database this plugin will use." )
    public String NAME = "core_Database";

    @Setting( value = "userDb", comment = "The name of the user database which this plugin will reference for authentication." )
    public String USER_DB = "user_Database";

    @Setting( value = "username", comment = "The username used for authentication." )
    public String USERNAME = "username";

    @Setting( value = "password", comment = "The password used for authentication." )
    public String PASSWORD = "password";

}
