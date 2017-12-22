package com.atherys.core;

import com.atherys.core.utils.Config;
import ninja.leaping.configurate.objectmapping.Setting;

import java.io.IOException;

public final class CoreConfig extends Config {

    @Setting( value = "defaultConfig")
    public boolean defaultConfig = true;

    @Setting( value = "database.host" )
    public String mongoHost = "localhost";

    @Setting( value = "database.port" )
    public int mongoPort = 27017;

    @Setting( value = "database.database" )
    public String mongoDatabase = "core_Database";

    @Setting( value = "database.user_database" )
    public String mongoUserDB = "user_Database";

    @Setting( value = "database.username" )
    public String mongoUsername = "username";

    @Setting( value = "database.password" )
    public String mongoPassword = "password";

    public CoreConfig() throws IOException {
        super( AtherysCore.getInstance().getWorkingDirectory() );
    }
}
