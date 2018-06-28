package com.atherys.core.script.lib.potion;

import com.atherys.core.utils.PentaFunction;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.effect.potion.PotionEffect;
import org.spongepowered.api.effect.potion.PotionEffectType;

public class CreatePotionEffect implements PentaFunction<String, Integer, Integer, Boolean, Boolean, PotionEffect> {

    @Override
    public PotionEffect apply(String effectId, Integer amplification, Integer durationTicks, Boolean isAmbient, Boolean hasParticles) {
        return Sponge.getRegistry().getType(PotionEffectType.class, effectId).map( type -> {
            return PotionEffect.builder()
                    .potionType(type)
                    .amplifier(amplification)
                    .duration(durationTicks)
                    .ambience(isAmbient)
                    .particles(hasParticles)
                    .build();
        }).orElse(null);
    }
}
