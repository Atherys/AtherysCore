package com.atherys.core.script.lib.task;

import org.spongepowered.api.scheduler.Task;

import java.util.function.BiFunction;

public class SetTaskDelay implements BiFunction<Task.Builder, Long, Boolean> {
    @Override
    public Boolean apply(Task.Builder builder, Long ticks) {
        builder.delayTicks(ticks);
        return true;
    }
}
