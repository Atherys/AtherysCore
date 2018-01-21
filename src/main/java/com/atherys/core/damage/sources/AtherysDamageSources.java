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

    public static AtherysIndirectEntityDamageSource indirect ( Entity source, Entity projectile, AtherysDamageType type) {
        return singleIndirectBuilder( source ).proxySource(projectile).type( type ).build();
    }



    // Melee
    public static AtherysEntitySingleDamageSource blunt ( Entity source ) {
        return singleDirect( source, AtherysDamageTypes.BLUNT );
    }

    public static AtherysEntitySingleDamageSource slash ( Entity source ) {
        return singleDirect( source, AtherysDamageTypes.SLASH );
    }

    public static AtherysEntitySingleDamageSource stab ( Entity source ) {
        return singleDirect( source, AtherysDamageTypes.STAB );
    }

    public static AtherysEntitySingleDamageSource unarmed ( Entity source ) {
        return singleDirect( source, AtherysDamageTypes.UNARMED );
    }

    // Ranged
    public static AtherysIndirectEntityDamageSource ballistic ( Entity source, Entity projectile ) {
        return indirect( source, projectile, AtherysDamageTypes.BALLISTIC );
    }

    public static AtherysIndirectEntityDamageSource piercing ( Entity source, Entity projectile ) {
        return indirect( source, projectile, AtherysDamageTypes.PIERCE );
    }

    // Magic
    public static AtherysEntitySingleDamageSource fire ( Entity source ) {
        return singleDirectMagic( source, AtherysDamageTypes.FIRE );
    }

    public static AtherysEntitySingleDamageSource ice ( Entity source ) {
        return singleDirectMagic( source, AtherysDamageTypes.ICE );
    }

    public static AtherysEntitySingleDamageSource arcane ( Entity source ) {
        return singleDirectMagic( source, AtherysDamageTypes.ARCANE );
    }

    public static AtherysEntitySingleDamageSource shock ( Entity source ) {
        return singleDirectMagic( source, AtherysDamageTypes.SHOCK );
    }

    public static AtherysEntitySingleDamageSource nature ( Entity source ) {
        return singleDirectMagic( source, AtherysDamageTypes.NATURE );
    }

    public static AtherysEntitySingleDamageSource mental ( Entity source ) {
        return singleDirectMagic( source, AtherysDamageTypes.MENTAL );
    }

    public static AtherysEntitySingleDamageSource radiant ( Entity source ) {
        return singleDirectMagic( source, AtherysDamageTypes.RADIANT );
    }

    public static AtherysEntitySingleDamageSource necrotic ( Entity source ) {
        return singleDirectMagic( source, AtherysDamageTypes.NECROTIC );
    }

    public static AtherysEntitySingleDamageSource blood ( Entity source ) {
        return singleDirectMagic( source, AtherysDamageTypes.BLOOD );
    }
}
