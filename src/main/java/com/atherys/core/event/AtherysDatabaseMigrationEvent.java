package com.atherys.core.event;

import com.atherys.core.AtherysCore;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.Event;
import org.spongepowered.api.event.cause.Cause;

import java.util.ArrayList;
import java.util.List;

public class AtherysDatabaseMigrationEvent implements Event {

    private Cause cause;

    private List<String> locations = new ArrayList<>();

    public AtherysDatabaseMigrationEvent() {
        this.cause = Cause.builder().append(AtherysCore.getInstance()).build(Sponge.getCauseStackManager().getCurrentContext());
    }

    public void addLocation(String migrationFolderLocation) {
        this.locations.add(migrationFolderLocation);
    }

    public List<String> getLocations() {
        return locations;
    }

    @Override
    public Cause getCause() {
        return cause;
    }
}
