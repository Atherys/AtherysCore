package com.atherys.core.database.mongo;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

/**
 * A configuration class to be used in conjunction with {@link com.atherys.core.utils.PluginConfig} for easily setting up a MongoDB configuration.
 */
@ConfigSerializable
public class MongoDatabaseConfig {

    @Setting(value = "host", comment = "The host ip address of the MongoDB Database.")
    public String HOST = "localhost";

    @Setting(value = "port", comment = "The port on which the MongoDB Database is running.")
    public int PORT = 27017;

    @Setting(value = "name", comment = "The name of the database A'therys Core will use.")
    public String NAME = "core_Database";

    @Setting(value = "userDb", comment = "The name of the user database which A'therys Core will reference for authentication.")
    public String USER_DB = "user_Database";

    @Setting(value = "username", comment = "The username used for authentication.")
    public String USERNAME = "username";

    @Setting(value = "password", comment = "The password used for authentication.")
    public String PASSWORD = "password";

}
