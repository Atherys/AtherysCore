package com.atherys.core.damage.sources;

import com.atherys.core.damage.AtherysDamageType;
import com.atherys.core.damage.AtherysDamageTypes;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.event.cause.entity.damage.DamageTypes;

public final class AtherysDamageSources {

    /**
     * Creates a multi damage source builder which distributes the damage amount uniformly across all sources provided
     * @param source The entity source of this damage
     * @param sources The damage source(s)
     * @return the builder
     */
    public static AtherysEntityMultiDamageSource.Builder of ( Entity source, AtherysDamageSource... sources ) {
        AtherysEntityMultiDamageSource.Builder builder = multipleDirectBuilder( source );
        float uniform = 1.0f / sources.length;
        for ( AtherysDamageSource src : sources ) {
            builder.addType( src, uniform );
        }
        return builder;
    }

    /**
     * Creates a single direct damage source builder
     * @param source the entity source of this damage
     * @return the builder
     */
    public static AtherysEntitySingleDamageSource.Builder singleDirectBuilder ( Entity source ) {
        return new AtherysEntitySingleDamageSource.Builder().type( DamageTypes.CUSTOM ).entity( source );
    }

    /**
     * Create a multi direct damage source builder
     * @param source the entity source of this damage
     * @return the builder
     */
    public static AtherysEntityMultiDamageSource.Builder multipleDirectBuilder ( Entity source ) {
        return new AtherysEntityMultiDamageSource.Builder().type( DamageTypes.CUSTOM ).entity( source );
    }

    public static AtherysIndirectEntityDamageSource.Builder singleIndirectBuilder ( Entity source ) {
        return new AtherysIndirectEntityDamageSource.Builder().type( DamageTypes.CUSTOM ).entity( source );
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
