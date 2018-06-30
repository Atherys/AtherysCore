package com.atherys.core.script.lib.task;

import com.atherys.core.script.lib.ScriptLibrary;

import javax.script.ScriptEngine;

public final class TaskLibrary implements ScriptLibrary {
    @Override
    public void registerObjects(ScriptEngine engine) {
        engine.put("buildTask", new TaskOf());
        engine.put("setTaskDelay", new SetTaskDelay());
        engine.put("setTaskInterval", new SetTaskInterval());
        engine.put("setTaskExecutable", new SetTaskExecutable());
        engine.put("startTask", new StartTask());
        engine.put("cancelTask", new CancelTask());
    }
}
