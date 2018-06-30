package com.atherys.core.script.lib.entity;

import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.living.Living;

import java.util.function.BiFunction;

public class SetEntityMaxHealth implements BiFunction<Entity, Double, Boolean> {
    public Boolean apply(Entity entity, Double maxHealth){
        if (!(entity instanceof Living)) return false;
        return entity.offer(Keys.MAX_HEALTH, maxHealth).isSuccessful();
    }
}
