package com.atherys.core.script;

import com.atherys.core.AtherysCore;
import com.atherys.core.script.lib.ScriptLibrary;

import javax.script.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public final class AtherysScript {

    private static AtherysScript instance = new AtherysScript();

    private ScriptEngine engine;

    private Set<ScriptLibrary> libraries = new HashSet<>();

    private AtherysScript() { }

    public ScriptEngine getEngine() {

        if ( engine == null ) {
            engine = new ScriptEngineManager().getEngineByName("nashorn");
            libraries.forEach( library -> library.registerObjects(engine) );
        }

        return engine;
    }

    public void compile(String script, Consumer<Invocable> consumer) {
        try {
            consumer.accept((Invocable) engine.eval(script));
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    public Object run(String script) throws ScriptException {
        return engine.eval(script);
    }

    public boolean addLibrary(ScriptLibrary library) {
        return libraries.add(library);
    }

    public void addLibraries(ScriptLibrary... libs) {
        libraries.addAll(Arrays.asList(libs));
    }

    public static AtherysScript getInstance() {
        return instance;
    }

}
