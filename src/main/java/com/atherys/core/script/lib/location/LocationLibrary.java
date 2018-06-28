package com.atherys.core.script.lib.location;

import com.atherys.core.script.lib.ScriptLibrary;

import javax.script.ScriptEngine;

public final class LocationLibrary implements ScriptLibrary {
    @Override
    public void registerObjects(ScriptEngine engine) {
        engine.put("getWorldFromName", new GetWorldFromName());
        engine.put("locationOf", new LocationOf());
    }
}
