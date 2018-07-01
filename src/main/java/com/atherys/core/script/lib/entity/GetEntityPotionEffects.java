package com.atherys.core.script.lib.entity;

import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.effect.potion.PotionEffect;
import org.spongepowered.api.entity.Entity;

import java.util.List;
import java.util.function.Function;

public class GetEntityPotionEffects implements Function<Entity, PotionEffect[]> {
    public PotionEffect[] apply(Entity entity){
        return (PotionEffect[])entity.get(Keys.POTION_EFFECTS).get().toArray();
    }
}
