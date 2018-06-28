package com.atherys.core.script.lib.text;

import com.atherys.core.script.lib.ScriptLibrary;

import javax.script.ScriptEngine;

public final class TextLibrary implements ScriptLibrary {
    @Override
    public void registerObjects(ScriptEngine engine) {
        engine.put("textOf", new TextOf());
        engine.put("color", new Color());
        engine.put("style", new Style());
    }
}
