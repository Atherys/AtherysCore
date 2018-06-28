package com.atherys.core.script.lib.entity;

import com.atherys.core.utils.TriFunction;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.event.cause.entity.damage.source.DamageSource;

public class DamageEntity implements TriFunction<Entity, DamageSource, Double, Boolean> {
    @Override
    public Boolean apply(Entity entity, DamageSource src, Double damage) {
        return entity.damage(damage, src);
    }
}
