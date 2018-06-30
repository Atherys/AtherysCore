package com.atherys.core.script.lib.task;

import org.spongepowered.api.scheduler.Task;

import java.util.function.Function;

public class TaskOf implements Function<String, Task.Builder> {
    @Override
    public Task.Builder apply(String name) {
        return Task.builder().name(name);
    }
}
