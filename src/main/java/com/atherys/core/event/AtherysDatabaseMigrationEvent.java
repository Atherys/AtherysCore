package com.atherys.core.event;

import com.atherys.core.AtherysCore;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.Event;
import org.spongepowered.api.event.cause.Cause;

public class AtherysDatabaseMigrationEvent implements Event {

    private Cause cause;

    private FluentConfiguration configuration;

    public AtherysDatabaseMigrationEvent(FluentConfiguration configuration) {
        this.cause = Cause.builder().append(AtherysCore.getInstance()).build(Sponge.getCauseStackManager().getCurrentContext());
        this.configuration = configuration;
    }

    public FluentConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public Cause getCause() {
        return cause;
    }
}
