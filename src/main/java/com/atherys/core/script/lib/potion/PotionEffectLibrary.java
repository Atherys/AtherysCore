package com.atherys.core.script.lib.potion;

import com.atherys.core.script.lib.ScriptLibrary;

import javax.script.ScriptEngine;

public final class PotionEffectLibrary implements ScriptLibrary {
    @Override
    public void registerObjects(ScriptEngine engine) {
        engine.put("createPotionEffect", new CreatePotionEffect());
    }
}
