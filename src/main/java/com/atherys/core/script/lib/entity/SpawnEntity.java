package com.atherys.core.script.lib.entity;

import org.spongepowered.api.entity.Entity;

import java.util.function.BiFunction;
import java.util.function.Function;

public class SpawnEntity implements Function<Entity, Boolean> {
    @Override
    public Boolean apply(Entity entity) {
        return entity.getWorld().spawnEntity(entity);
    }
}
