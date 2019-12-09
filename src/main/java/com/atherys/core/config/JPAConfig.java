package com.atherys.core.config;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;
import org.hibernate.cfg.Environment;

import java.util.HashMap;
import java.util.Map;

@ConfigSerializable
public final class JPAConfig {

    @Setting("hibernate")
    public Map<String,String> HIBERNATE = new HashMap<>();
    {
        HIBERNATE.put(Environment.CONNECTION_PREFIX + ".driver_class", "org.postgresql.Driver");
        HIBERNATE.put(Environment.CONNECTION_PREFIX + ".url", "jdbc:postgresql://127.0.0.1:5432/atherys");
        HIBERNATE.put(Environment.CONNECTION_PREFIX + ".username", "postgre");
        HIBERNATE.put(Environment.CONNECTION_PREFIX + ".password", "");

        HIBERNATE.put(Environment.POOL_SIZE, "10");
        HIBERNATE.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
        HIBERNATE.put(Environment.SHOW_SQL, "true");
        HIBERNATE.put(Environment.HBM2DDL_AUTO, "update");
    }

}
