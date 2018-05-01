package com.atherys.core;

import com.atherys.core.damage.DamageConfig;
import com.atherys.core.database.config.DatabaseConfig;
import com.atherys.core.utils.PluginConfig;
import ninja.leaping.configurate.objectmapping.Setting;

import java.io.IOException;

public final class CoreConfig extends PluginConfig {

    @Setting( value = "defaultConfig", comment = "Whether or not this is the default config. If this is set to true, the plugin will not start." )
    public boolean DEFAULT = true;

    @Setting( "database" )
    public DatabaseConfig DATABASE = new DatabaseConfig();

    @Setting( "damage" )
    public DamageConfig DAMAGE = new DamageConfig();

    CoreConfig() throws IOException {
        super( AtherysCore.getInstance().getWorkingDirectory(), "config.conf" );
    }
}
