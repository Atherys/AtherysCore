package com.atherys.core.damage.sources;

import com.atherys.core.damage.AtherysDamageType;
import com.atherys.core.damage.AtherysDamageTypes;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.event.cause.entity.damage.DamageTypes;

public final class AtherysDamageSources {
    /**
     * Creates a single direct damage source builder
     * @param source the entity source of this damage
     * @return the builder
     */
    private static AtherysDirectEntityDamageSource.Builder singleDirectBuilder (Entity source ) {
        return new AtherysDirectEntityDamageSource.Builder().type( DamageTypes.CUSTOM ).entity( source );
    }

    private static AtherysIndirectEntityDamageSource.Builder singleIndirectBuilder ( Entity source ) {
        return new AtherysIndirectEntityDamageSource.Builder().type( DamageTypes.CUSTOM ).entity( source );
    }

    private static AtherysDirectEntityDamageSource singleDirect (Entity source, AtherysDamageType type) {
        return singleDirectBuilder( source ).type( type ).build();
    }

    private static AtherysDirectEntityDamageSource singleDirectMagic (Entity source, AtherysDamageType type ) {
        return singleDirectBuilder( source ).type( type ).magical().build();
    }

    private static AtherysIndirectEntityDamageSource indirect ( Entity source, Entity projectile, AtherysDamageType type) {
        return singleIndirectBuilder( source ).proxySource(projectile).type( type ).build();
    }

    // Melee
    public static AtherysDirectEntityDamageSource blunt (Entity source ) {
        return singleDirect( source, AtherysDamageTypes.BLUNT );
    }

    public static AtherysDirectEntityDamageSource slash (Entity source ) {
        return singleDirect( source, AtherysDamageTypes.SLASH );
    }

    public static AtherysDirectEntityDamageSource stab (Entity source ) {
        return singleDirect( source, AtherysDamageTypes.STAB );
    }

    public static AtherysDirectEntityDamageSource unarmed (Entity source ) {
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
    public static AtherysDirectEntityDamageSource fire (Entity source ) {
        return singleDirectMagic( source, AtherysDamageTypes.FIRE );
    }

    public static AtherysDirectEntityDamageSource ice (Entity source ) {
        return singleDirectMagic( source, AtherysDamageTypes.ICE );
    }

    public static AtherysDirectEntityDamageSource arcane (Entity source ) {
        return singleDirectMagic( source, AtherysDamageTypes.ARCANE );
    }

    public static AtherysDirectEntityDamageSource shock (Entity source ) {
        return singleDirectMagic( source, AtherysDamageTypes.SHOCK );
    }

    public static AtherysDirectEntityDamageSource nature (Entity source ) {
        return singleDirectMagic( source, AtherysDamageTypes.NATURE );
    }

    public static AtherysDirectEntityDamageSource mental (Entity source ) {
        return singleDirectMagic( source, AtherysDamageTypes.MENTAL );
    }

    public static AtherysDirectEntityDamageSource radiant (Entity source ) {
        return singleDirectMagic( source, AtherysDamageTypes.RADIANT );
    }

    public static AtherysDirectEntityDamageSource necrotic (Entity source ) {
        return singleDirectMagic( source, AtherysDamageTypes.NECROTIC );
    }

    public static AtherysDirectEntityDamageSource blood (Entity source ) {
        return singleDirectMagic( source, AtherysDamageTypes.BLOOD );
    }
}
