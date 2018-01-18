package com.atherys.core.damage;

import org.spongepowered.api.event.cause.entity.damage.source.DamageSource;

public final class AtherysDamageSources {

    // Melee
    public final DamageSource BLUNT_DAMAGE = DamageSource.builder().type(AtherysDamageTypes.BLUNT).build();

    public final DamageSource SLASH_DAMAGE = DamageSource.builder().type(AtherysDamageTypes.SLASH).build();

    public final DamageSource STAB_DAMAGE = DamageSource.builder().type(AtherysDamageTypes.STAB).build();

    public final DamageSource UNARMED_DAMAGE = DamageSource.builder().type(AtherysDamageTypes.UNARMED).build();

    // Ranged
    public final DamageSource BALLISTIC_DAMAGE = DamageSource.builder().type(AtherysDamageTypes.BALLISTIC).build();

    public final DamageSource PIERCING_DAMAGE = DamageSource.builder().type(AtherysDamageTypes.PIERCE).build();

    // Magic
    public final DamageSource FIRE_DAMAGE = DamageSource.builder().type(AtherysDamageTypes.FIRE).magical().build();

    public final DamageSource ICE_DAMAGE = DamageSource.builder().type(AtherysDamageTypes.ICE).magical().build();

    public final DamageSource SHOCK_DAMAGE = DamageSource.builder().type(AtherysDamageTypes.SHOCK).magical().build();

    public final DamageSource NATURE_DAMAGE = DamageSource.builder().type(AtherysDamageTypes.NATURE).magical().build();

    public final DamageSource MENTAL_DAMAGE = DamageSource.builder().type(AtherysDamageTypes.MENTAL).magical().build();

    public final DamageSource RADIANT_DAMAGE = DamageSource.builder().type(AtherysDamageTypes.RADIANT).magical().build();

    public final DamageSource NECROTIC_DAMAGE = DamageSource.builder().type(AtherysDamageTypes.NECROTIC).magical().build();

    public final DamageSource BLOOD_DAMAGE = DamageSource.builder().type(AtherysDamageTypes.BLOOD).magical().build();
}
