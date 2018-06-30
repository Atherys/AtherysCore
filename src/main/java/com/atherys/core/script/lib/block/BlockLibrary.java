package com.atherys.core.script.lib.block;

import com.atherys.core.script.lib.ScriptLibrary;

import javax.script.ScriptEngine;

public final class BlockLibrary implements ScriptLibrary {
    @Override
    public void registerObjects(ScriptEngine engine) {
        engine.put("blockOf", new BlockOf());
        engine.put("getBlockFromLocation", new GetBlockFromLocation());
        engine.put("setBlockAtLocation", new SetBlockAtLocation());
    }
}
