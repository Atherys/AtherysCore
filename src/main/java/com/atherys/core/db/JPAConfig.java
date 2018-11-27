package com.atherys.core.db;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;
import org.hibernate.cfg.Configuration;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.util.Properties;

@ConfigSerializable
public final class JPAConfig {

    @ConfigSerializable
    public static class DatabaseConfig {
        @Setting("database-name")
        public String NAME = "atheryscore";

        @Setting("database-user")
        public String USER = "postgre";

        @Setting("database-password")
        public String PASSWORD = "";
    }

    @ConfigSerializable
    public static class HibernateConfig {
        @Setting("hibernate-dialect")
        public String DIALECT = "org.hibernate.dialect.PostgreSQLDialect";

        @Setting("hibernate-driver")
        public String DRIVER = "org.postgresql.Driver";

        @Setting("hibernate-hdm2ddl-auto")
        public String HDM2DDL_AUTO = "update";
    }

    @Setting("database")
    public DatabaseConfig DB = new DatabaseConfig();

    @Setting("hibernate")
    public HibernateConfig HIBERNATE = new HibernateConfig();

    public DataSource getDataSource() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();

        dataSource.setDatabaseName(DB.NAME);
        dataSource.setUser(DB.USER);
        dataSource.setPassword(DB.PASSWORD);

        return dataSource;
    }

    public Configuration getHibernateConfiguration() {
        Configuration properties = new Configuration();

        properties.setProperty("hibernate.dialect", HIBERNATE.DIALECT);
        properties.setProperty("hibernate.connection.driver_class", HIBERNATE.DRIVER);
        properties.setProperty("hibernate.hbm2ddl.auto", HIBERNATE.HDM2DDL_AUTO);

        return properties;
    }

}
