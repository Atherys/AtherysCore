package com.atherys.core.script.lib.entity;

import com.atherys.core.script.lib.ScriptLibrary;

import javax.script.ScriptEngine;

public final class EntityLibrary implements ScriptLibrary {
    @Override
    public void registerObjects(ScriptEngine engine) {
        engine.put("createEntity", new CreateEntity());
        engine.put("spawnEntity", new SpawnEntity());
    }
}
