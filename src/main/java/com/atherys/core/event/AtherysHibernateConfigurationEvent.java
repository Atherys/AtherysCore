package com.atherys.core.event;

import org.hibernate.cfg.Configuration;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.Event;
import org.spongepowered.api.event.cause.Cause;

public class AtherysHibernateConfigurationEvent implements Event {

    private Cause cause;

    private Configuration configuration;

    public AtherysHibernateConfigurationEvent(Configuration configuration) {
        this.cause = Cause.builder().build(Sponge.getCauseStackManager().getCurrentContext());
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    @Override
    public Cause getCause() {
        return cause;
    }
}
