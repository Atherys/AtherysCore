package com.atherys.core.db;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;
import org.hibernate.cfg.Environment;

import java.util.HashMap;
import java.util.Map;

@ConfigSerializable
public final class JPAConfig {

    public static final String DRIVER_CLASS_KEY = Environment.CONNECTION_PREFIX + ".driver_class";

    public static final String URL_KEY = Environment.CONNECTION_PREFIX + ".url";

    public static final String USERNAME_KEY = Environment.CONNECTION_PREFIX + ".username";

    public static final String PASSWORD_KEY = Environment.CONNECTION_PREFIX + ".password";

    @Setting("hibernate")
    public Map<String,String> HIBERNATE = new HashMap<>();
    {
        HIBERNATE.put(DRIVER_CLASS_KEY, "org.postgresql.Driver");
        HIBERNATE.put(URL_KEY, "jdbc:postgresql://127.0.0.1:5432/atherys");
        HIBERNATE.put(USERNAME_KEY, "postgre");
        HIBERNATE.put(PASSWORD_KEY, "");

        HIBERNATE.put(Environment.POOL_SIZE, "10");
        HIBERNATE.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
        HIBERNATE.put(Environment.SHOW_SQL, "true");
        HIBERNATE.put(Environment.HBM2DDL_AUTO, "update");
    }

}
