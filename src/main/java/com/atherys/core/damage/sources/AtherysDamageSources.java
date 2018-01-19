package com.atherys.core.damage.sources;

import com.atherys.core.damage.AtherysDamageType;
import com.atherys.core.damage.AtherysDamageTypes;
import org.spongepowered.api.entity.Entity;

public final class AtherysDamageSources {


    public static AtherysEntitySingleDamageSource.Builder singleDirectBuilder ( Entity source ) {
        return new AtherysEntitySingleDamageSource.Builder().entity( source );
    }

    public static AtherysEntityMultiDamageSource.Builder multipleDirectBuilder ( Entity source ) {
        return new AtherysEntityMultiDamageSource.Builder().entity( source );
    }

    public static AtherysIndirectEntityDamageSource.Builder singleIndirectBuilder ( Entity source ) {
        return new AtherysIndirectEntityDamageSource.Builder().entity( source );
    }

    public static AtherysEntitySingleDamageSource singleDirect (Entity source, AtherysDamageType type) {
        return singleDirectBuilder( source ).type( type ).build();
    }

    public static AtherysEntitySingleDamageSource singleDirectMagic ( Entity source, AtherysDamageType type ) {
        return singleDirectBuilder( source ).type( type ).magical().build();
    }

    public static AtherysIndirectEntityDamageSource indirect (Entity source, AtherysDamageType type) {
        return singleIndirectBuilder( source ).type( type ).build();
    }



    // Melee
    public static AtherysEntitySingleDamageSource bluntDamage ( Entity source ) {
        return singleDirect( source, AtherysDamageTypes.BLUNT );
    }

    public static AtherysEntitySingleDamageSource slashDamage ( Entity source ) {
        return singleDirect( source, AtherysDamageTypes.SLASH );
    }

    public static AtherysEntitySingleDamageSource stabDamage ( Entity source ) {
        return singleDirect( source, AtherysDamageTypes.STAB );
    }

    public static AtherysEntitySingleDamageSource unarmedDamage ( Entity source ) {
        return singleDirect( source, AtherysDamageTypes.UNARMED );
    }

    // Ranged
    public static AtherysIndirectEntityDamageSource ballisticDamage ( Entity source ) {
        return indirect( source, AtherysDamageTypes.BALLISTIC );
    }

    public static AtherysIndirectEntityDamageSource piercingDamage ( Entity source ) {
        return indirect( source, AtherysDamageTypes.PIERCE );
    }

    // Magic
    public static AtherysEntitySingleDamageSource fireDamage ( Entity source ) {
        return singleDirectMagic( source, AtherysDamageTypes.FIRE );
    }

    public static AtherysEntitySingleDamageSource iceDamage ( Entity source ) {
        return singleDirectMagic( source, AtherysDamageTypes.ICE );
    }

    public static AtherysEntitySingleDamageSource arcaneDamage ( Entity source ) {
        return singleDirectMagic( source, AtherysDamageTypes.ARCANE );
    }

    public static AtherysEntitySingleDamageSource shockDamage ( Entity source ) {
        return singleDirectMagic( source, AtherysDamageTypes.SHOCK );
    }

    public static AtherysEntitySingleDamageSource natureDamage ( Entity source ) {
        return singleDirectMagic( source, AtherysDamageTypes.NATURE );
    }

    public static AtherysEntitySingleDamageSource mentalDamage ( Entity source ) {
        return singleDirectMagic( source, AtherysDamageTypes.MENTAL );
    }

    public static AtherysEntitySingleDamageSource radiantDamage ( Entity source ) {
        return singleDirectMagic( source, AtherysDamageTypes.RADIANT );
    }

    public static AtherysEntitySingleDamageSource necroticDamage ( Entity source ) {
        return singleDirectMagic( source, AtherysDamageTypes.NECROTIC );
    }

    public static AtherysEntitySingleDamageSource bloodDamage ( Entity source ) {
        return singleDirectMagic( source, AtherysDamageTypes.BLOOD );
    }
}
