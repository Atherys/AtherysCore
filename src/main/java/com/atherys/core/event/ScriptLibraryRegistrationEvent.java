package com.atherys.core.event;

import com.atherys.core.script.AtherysScript;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.Event;
import org.spongepowered.api.event.cause.Cause;

public class ScriptLibraryRegistrationEvent implements Event {

    private Cause cause;

    private AtherysScript scriptEngine;

    public ScriptLibraryRegistrationEvent(AtherysScript instance) {
        scriptEngine = instance;
        cause = Cause.of(Sponge.getCauseStackManager().getCurrentContext(), scriptEngine);
    }

    public AtherysScript getScriptEngine() {
        return scriptEngine;
    }

    @Override
    public Cause getCause() {
        return cause;
    }
}
