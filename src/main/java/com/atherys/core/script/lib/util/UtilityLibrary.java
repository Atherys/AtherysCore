package com.atherys.core.script.lib.util;

import com.atherys.core.script.lib.ScriptLibrary;

import javax.script.ScriptEngine;

public final class UtilityLibrary implements ScriptLibrary {

    @Override
    public void registerObjects(ScriptEngine engine) {
        engine.put("uuidOf", new UUIDOf());
        engine.put("randomUUID", new RandomUUID());
    }
}
