package com.atherys.core.db;

import com.atherys.core.event.AtherysHibernateConfigurationEvent;
import com.google.inject.Singleton;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.spongepowered.api.Sponge;

import javax.persistence.EntityManagerFactory;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class DatabaseContext {

    public DatabaseContext() {
    }

    public EntityManagerFactory createEntityManagerFactory(JPAConfig config) {
        MetadataSources metadataSources = new MetadataSources(configureServiceRegistry(config));

        addClasses(metadataSources);

        return metadataSources.buildMetadata()
                .getSessionFactoryBuilder()
                .build();
    }

    public ServiceRegistry configureServiceRegistry(JPAConfig config) {
        return new StandardServiceRegistryBuilder()
                .applySettings(getProperties(config))
                .build();
    }

    public Properties getProperties(JPAConfig config) {
        Properties properties = new Properties();

        config.HIBERNATE.forEach(properties::setProperty);

        return properties;
    }

    public void addClasses(MetadataSources metadataSources) {
        List<Class<?>> classes = new LinkedList<>();

        AtherysHibernateConfigurationEvent event = new AtherysHibernateConfigurationEvent(classes);
        Sponge.getEventManager().post(event);

        classes.forEach(metadataSources::addAnnotatedClass);
    }

}
