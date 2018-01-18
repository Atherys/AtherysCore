package com.atherys.core.damage.sources;

import com.atherys.core.damage.AtherysDamageType;
import com.atherys.core.damage.AtherysDamageTypes;
import org.spongepowered.api.entity.Entity;

public final class AtherysDamageSources {

    public static AtherysEntityDamageSource.Builder directBuilder ( Entity source ) {
        return new AtherysEntityDamageSource.Builder().entity( source );
    }

    public static AtherysIndirectEntityDamageSource.Builder indirectBuilder ( Entity source ) {
        return new AtherysIndirectEntityDamageSource.Builder().entity( source );
    }

    public static AtherysEntityDamageSource direct (Entity source, AtherysDamageType type) {
        return directBuilder( source ).type( type ).build();
    }

    public static AtherysEntityDamageSource directMagic ( Entity source, AtherysDamageType type ) {
        return directBuilder( source ).type( type ).magical().build();
    }

    public static AtherysIndirectEntityDamageSource indirect (Entity source, AtherysDamageType type) {
        return indirectBuilder( source ).type( type ).build();
    }

    public static AtherysIndirectEntityDamageSource indirectMagic ( Entity source, AtherysDamageType type ) {
        return indirectBuilder( source ).type( type ).magical().build();
    }

    // Melee
    public static AtherysEntityDamageSource bluntDamage ( Entity source ) {
        return direct( source, AtherysDamageTypes.BLUNT );
    }

    public static AtherysEntityDamageSource slashDamage ( Entity source ) {
        return direct( source, AtherysDamageTypes.SLASH );
    }

    public static AtherysEntityDamageSource stabDamage ( Entity source ) {
        return direct( source, AtherysDamageTypes.STAB );
    }

    public static AtherysEntityDamageSource unarmedDamage ( Entity source ) {
        return direct( source, AtherysDamageTypes.UNARMED );
    }

    // Ranged
    public static AtherysIndirectEntityDamageSource ballisticDamage ( Entity source ) {
        return indirect( source, AtherysDamageTypes.BALLISTIC );
    }

    public static AtherysIndirectEntityDamageSource piercingDamage ( Entity source ) {
        return indirect( source, AtherysDamageTypes.PIERCE );
    }

    // Magic
    public static AtherysEntityDamageSource fireDamage ( Entity source ) {
        return directMagic( source, AtherysDamageTypes.FIRE );
    }

    public static AtherysEntityDamageSource iceDamage ( Entity source ) {
        return directMagic( source, AtherysDamageTypes.ICE );
    }

    public static AtherysEntityDamageSource shockDamage ( Entity source ) {
        return directMagic( source, AtherysDamageTypes.SHOCK );
    }

    public static AtherysEntityDamageSource natureDamage ( Entity source ) {
        return directMagic( source, AtherysDamageTypes.NATURE );
    }

    public static AtherysEntityDamageSource mentalDamage ( Entity source ) {
        return directMagic( source, AtherysDamageTypes.MENTAL );
    }

    public static AtherysEntityDamageSource necroticDamage ( Entity source ) {
        return directMagic( source, AtherysDamageTypes.NECROTIC );
    }

    public static AtherysEntityDamageSource bloodDamage ( Entity source ) {
        return directMagic( source, AtherysDamageTypes.BLOOD );
    }
}
