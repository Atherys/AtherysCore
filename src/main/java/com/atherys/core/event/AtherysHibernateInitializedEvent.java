package com.atherys.core.event;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.Event;
import org.spongepowered.api.event.cause.Cause;

import javax.persistence.EntityManagerFactory;

public class AtherysHibernateInitializedEvent implements Event {

    private Cause cause;

    private EntityManagerFactory entityManagerFactory;

    public AtherysHibernateInitializedEvent(EntityManagerFactory entityManagerFactory) {
        this.cause = Cause.builder().build(Sponge.getCauseStackManager().getCurrentContext());
        this.entityManagerFactory = entityManagerFactory;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    @Override
    public Cause getCause() {
        return cause;
    }

}
