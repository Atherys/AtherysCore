package com.atherys.core.script.lib.entity;

import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.manipulator.mutable.PotionEffectData;
import org.spongepowered.api.effect.potion.PotionEffect;
import org.spongepowered.api.entity.Entity;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class SetEntityPotionEffects implements BiFunction<Entity, PotionEffect[],Boolean> {
    public Boolean apply(Entity entity, PotionEffect[] effects){
        return entity.offer(Keys.POTION_EFFECTS, Arrays.asList(effects)).isSuccessful();
    }
}
