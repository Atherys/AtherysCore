package com.atherys.core.script.lib.player;

import com.atherys.core.script.lib.ScriptLibrary;

import javax.script.ScriptEngine;

public final class PlayerLibrary implements ScriptLibrary {
    @Override
    public void registerObjects(ScriptEngine engine) {
        engine.put("getPlayerFromName", new GetPlayerFromName());
        engine.put("getPlayerFromUUID", new GetPlayerFromUUID());
        engine.put("getPlayerLocation", new GetPlayerLocation());
        engine.put("getPlayerUUID", new GetPlayerUUID());
        engine.put("teleportPlayer", new TeleportPlayer());
    }
}
