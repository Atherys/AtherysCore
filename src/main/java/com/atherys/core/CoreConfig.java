package com.atherys.core;

import com.atherys.core.utils.Config;
import ninja.leaping.configurate.objectmapping.Setting;

import java.io.IOException;

public final class CoreConfig extends Config {

    @Setting( value = "defaultConfig", comment = "Whether or not this is the default config. If this is set to true, the plugin will not start.")
    public boolean defaultConfig = true;

    @Setting( value = "dbHost", comment = "The host ip address of the MongoDB Database.")
    public String mongoHost = "localhost";

    @Setting( value = "dbPort", comment = "The port on which the MongoDB Database is running." )
    public int mongoPort = 27017;

    @Setting( value = "dbDatabase", comment = "The name of the database A'therys Core will use." )
    public String mongoDatabase = "core_Database";

    @Setting( value = "dbUserDatabase", comment = "The name of the user database which A'therys Core will reference for authentication." )
    public String mongoUserDB = "user_Database";

    @Setting( value = "dbUsername", comment = "The username used for authentication." )
    public String mongoUsername = "username";

    @Setting( value = "dbPassword", comment = "The password used for authentication." )
    public String mongoPassword = "password";

    public CoreConfig() throws IOException {
        super( AtherysCore.getInstance().getWorkingDirectory() );
    }
}
