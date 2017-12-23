package com.atherys.core;

import com.atherys.core.utils.PluginConfig;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.ItemTypes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class CoreConfig extends PluginConfig {

    @Setting( value = "defaultConfig", comment = "Whether or not this is the default config. If this is set to true, the plugin will not start.")
    public boolean DEFAULT = true;

    @Setting( value = "catalogueListTest" )
    public List<ItemType> test = new ArrayList<>();
    {
        test.add(ItemTypes.ACACIA_BOAT);
        test.add(ItemTypes.APPLE);
        test.add(ItemTypes.BLACK_SHULKER_BOX);
    }

    @Setting( value = "catalogueMapTest")
    public Map<String, ItemType> mapTest = new HashMap<>();
    {
        mapTest.put( "test1", ItemTypes.ACTIVATOR_RAIL );
        mapTest.put( "test2", ItemTypes.ARROW );
        mapTest.put( "test3", ItemTypes.BIRCH_FENCE );
    }

    @Setting( value = "database" )
    public DatabaseConfig DATABASE = new DatabaseConfig();

    @ConfigSerializable
    public class DatabaseConfig {

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
