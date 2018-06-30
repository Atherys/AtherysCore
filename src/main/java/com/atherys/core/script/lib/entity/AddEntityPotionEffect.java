package com.atherys.core.script.lib.entity;

import org.spongepowered.api.data.manipulator.mutable.PotionEffectData;
import org.spongepowered.api.effect.potion.PotionEffect;
import org.spongepowered.api.entity.Entity;

import java.util.function.BiFunction;

public class AddEntityPotionEffect implements BiFunction<Entity, PotionEffect, Boolean> {
    public Boolean apply(Entity entity, PotionEffect potionEffect){
        PotionEffectData effects = entity.getOrCreate(PotionEffectData.class).get();
        effects.addElement(potionEffect);
        return entity.offer(effects).isSuccessful();
    }
}
