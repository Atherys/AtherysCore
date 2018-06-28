package com.atherys.core.script.lib.task;

import com.atherys.core.AtherysCore;
import org.spongepowered.api.scheduler.Task;

import java.util.function.Function;

public class StartTask implements Function<Task.Builder, Task> {
    @Override
    public Task apply(Task.Builder builder) {
        return builder.submit(AtherysCore.getInstance());
    }
}
