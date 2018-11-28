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
        public String ADDRESS = "127.0.0.1";

        @Setting("port")
        public int PORT = 5432;

        @Setting("database-name")
        public String NAME = "atherys";

        @Setting("database-user")
        public String USER = "postgre";

        @Setting("database-password")
        public String PASSWORD = "pwd";
    }

    @ConfigSerializable
    public static class HibernateConfig {
        @Setting("hibernate-dialect")
        public String DIALECT = "org.hibernate.dialect.PostgreSQLDialect";

        @Setting("hibernate-driver")
        public String DRIVER = "org.postgresql.Driver";

        @Setting("hibernate-hbm2ddl-auto")
        public String HBM2DDL_AUTO = "update";
    }

    @Setting("database")
    public DatabaseConfig DB = new DatabaseConfig();

    @Setting("hibernate")
    public HibernateConfig HIBERNATE = new HibernateConfig();

    public DataSource getDataSource() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();

        dataSource.setUrl(DB.ADDRESS);
        dataSource.setPortNumber(DB.PORT);
        dataSource.setDatabaseName(DB.NAME);
        dataSource.setUser(DB.USER);
        dataSource.setPassword(DB.PASSWORD);

        return dataSource;
    }

    public Configuration getHibernateConfiguration() {
        Configuration properties = new Configuration();

        properties.setProperty(Environment.DIALECT, HIBERNATE.DIALECT);
        properties.setProperty(Environment.DRIVER, HIBERNATE.DRIVER);
        properties.setProperty(Environment.HBM2DDL_AUTO, HIBERNATE.HBM2DDL_AUTO);

        return properties;
    }

}
