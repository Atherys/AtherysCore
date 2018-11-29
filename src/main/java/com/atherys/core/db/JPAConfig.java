package com.atherys.core.db;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.util.Properties;

@ConfigSerializable
public final class JPAConfig {

    @ConfigSerializable
    public static class DatabaseConfig {
        @Setting("database-address")
        public String ADDRESS = "jdbc:postgresql://127.0.0.1:5432/atherys";

        @Setting("database-user")
        public String USER = "postgre";

        @Setting("database-password")
        public String PASSWORD = "pwd";

        @Setting("database-driver")
        public String DRIVER = "org.postgresql.Driver";
    }

    @ConfigSerializable
    public static class HibernateConfig {
        @Setting("hibernate-dialect")
        public String DIALECT = "org.hibernate.dialect.PostgreSQLDialect";

        @Setting("hibernate-hbm2ddl-auto")
        public String HBM2DDL_AUTO = "update";
    }

    @Setting("database")
    public DatabaseConfig DB = new DatabaseConfig();

    @Setting("hibernate")
    public HibernateConfig HIBERNATE = new HibernateConfig();

    public Configuration getHibernateConfiguration() {
        Configuration properties = new Configuration();

        properties.setProperty(Environment.URL, DB.ADDRESS);
        properties.setProperty(Environment.USER, DB.USER);
        properties.setProperty(Environment.PASS, DB.PASSWORD);
        properties.setProperty(Environment.DRIVER, DB.DRIVER);

        properties.setProperty(Environment.DIALECT, HIBERNATE.DIALECT);
        properties.setProperty(Environment.HBM2DDL_AUTO, HIBERNATE.HBM2DDL_AUTO);

        return properties;
    }

}
