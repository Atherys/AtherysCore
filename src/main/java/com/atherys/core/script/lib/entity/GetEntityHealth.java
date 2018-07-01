package com.atherys.core.script.lib.entity;

import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.living.Living;

import java.util.function.Function;

public class GetEntityHealth implements Function<Entity, Double> {
    public Double apply(Entity entity){
        if (!(entity instanceof Living)) return -1.0;
        return ((Living) entity).health().get();
    }
}
