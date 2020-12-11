package com.atherys.core.event;

import com.atherys.core.AtherysCore;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.Event;
import org.spongepowered.api.event.cause.Cause;

import java.util.ArrayList;
import java.util.List;

public class AtherysDatabaseMigrationEvent implements Event {

    private Cause cause;

    private List<String> pluginIds = new ArrayList<>();

    public AtherysDatabaseMigrationEvent() {
        this.cause = Cause.builder().append(AtherysCore.getInstance()).build(Sponge.getCauseStackManager().getCurrentContext());
    }

    public void registerForMigration(String pluginId) {
        this.pluginIds.add(pluginId);
    }

    public List<String> getPluginIds() {
        return pluginIds;
    }

    @Override
    public Cause getCause() {
        return cause;
    }
}
