package com.atherys.core.script.lib.entity;

import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.living.Living;

import java.util.Optional;
import java.util.function.BiFunction;

public class HealEntity implements BiFunction<Entity, Double, Boolean> {
    @Override
    public Boolean apply(Entity entity, Double amount) {
        return entity instanceof Living && entity.get(Keys.HEALTH).filter(health -> entity.offer(Keys.HEALTH, health + amount).isSuccessful()).isPresent();
    }
}
