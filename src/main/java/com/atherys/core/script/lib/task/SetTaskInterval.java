package com.atherys.core.script.lib.task;

import org.spongepowered.api.scheduler.Task;

import java.util.function.BiFunction;

public class SetTaskInterval implements BiFunction<Task.Builder, Long, Boolean> {
    @Override
    public Boolean apply(Task.Builder builder, Long ticks) {
        builder.intervalTicks(ticks);
        return true;
    }
}
