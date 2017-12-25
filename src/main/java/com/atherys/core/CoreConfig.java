package com.atherys.core;

import com.atherys.core.utils.PluginConfig;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

import java.io.IOException;

public final class CoreConfig extends PluginConfig {

    @Setting( value = "defaultConfig", comment = "Whether or not this is the default config. If this is set to true, the plugin will not start.")
    public boolean DEFAULT = true;

    @Setting( value = "database" )
    public DatabaseConfig DATABASE = new DatabaseConfig();

    @ConfigSerializable
    public static class DatabaseConfig {

        DatabaseConfig() {}

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

    public CoreConfig() throws IOException {
        super( AtherysCore.getInstance().getWorkingDirectory(), "config.conf" );
    }
}
