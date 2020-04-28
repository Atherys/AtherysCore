package com.atherys.core.db;

import com.atherys.core.db.migration.DatabaseMigrator;
import com.atherys.core.event.AtherysHibernateConfigurationEvent;
import com.atherys.core.event.AtherysHibernateInitializedEvent;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;

import javax.persistence.EntityManagerFactory;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class DatabaseContext implements AutoCloseable {

    private JPAConfig config;

    private EntityManagerFactory entityManagerFactory;

    public DatabaseContext(JPAConfig config, Logger logger) {
        this.config = config;

        DatabaseMigrator migrator = new DatabaseMigrator(config, logger);
        migrator.migrate();

        createEntityManagerFactory();
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    private void createEntityManagerFactory() {
        MetadataSources metadataSources = new MetadataSources(configureServiceRegistry(config));

        addClasses(metadataSources);

        entityManagerFactory = metadataSources.buildMetadata()
                .getSessionFactoryBuilder()
                .build();

        Sponge.getEventManager().post(new AtherysHibernateInitializedEvent(entityManagerFactory));
    }

    private ServiceRegistry configureServiceRegistry(JPAConfig config) {
        return new StandardServiceRegistryBuilder()
                .applySettings(getProperties(config))
                .build();
    }

    private Properties getProperties(JPAConfig config) {
        Properties properties = new Properties();

        config.HIBERNATE.forEach(properties::setProperty);

        return properties;
    }

    private void addClasses(MetadataSources metadataSources) {
        List<Class<?>> classes = new LinkedList<>();

        AtherysHibernateConfigurationEvent event = new AtherysHibernateConfigurationEvent(classes);
        Sponge.getEventManager().post(event);

        classes.forEach(metadataSources::addAnnotatedClass);
    }

    @Override
    public void close() {
        if (entityManagerFactory != null ) {
            entityManagerFactory.close();
        }
    }
}
