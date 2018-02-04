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
    public static AtherysDirectEntityDamageSource.Builder directBuilder ( Entity source ) {
        return new AtherysDirectEntityDamageSource.Builder().type( DamageTypes.CUSTOM ).entity( source );
    }

    public static AtherysIndirectEntityDamageSource.Builder indirectBuilder ( Entity source, Entity proxy ) {
        return new AtherysIndirectEntityDamageSource.Builder().type( DamageTypes.CUSTOM ).entity( source ).proxySource( proxy );
    }

    public static AtherysDirectEntityDamageSource direct ( Entity source, AtherysDamageType type) {
        return directBuilder( source ).type( type.getPrimitive() ).atherysType( type ).build();
    }

    public static AtherysDirectEntityDamageSource directMagic ( Entity source, AtherysDamageType type ) {
        return directBuilder( source ).type( type.getPrimitive() ).atherysType( type ).magical().build();
    }

    public static AtherysIndirectEntityDamageSource indirect ( Entity source, Entity projectile, AtherysDamageType type) {
        return indirectBuilder( source, projectile ).type( type.getPrimitive() ).atherysType( type ).build();
    }

    // Melee
    public static AtherysDirectEntityDamageSource blunt ( Entity source ) {
        return direct( source, AtherysDamageTypes.BLUNT );
    }

    public static AtherysDirectEntityDamageSource slash ( Entity source ) {
        return direct( source, AtherysDamageTypes.SLASH );
    }

    public static AtherysDirectEntityDamageSource stab ( Entity source ) {
        return direct( source, AtherysDamageTypes.STAB );
    }

    public static AtherysDirectEntityDamageSource unarmed ( Entity source ) {
        return direct( source, AtherysDamageTypes.UNARMED );
    }

    // Ranged
    public static AtherysIndirectEntityDamageSource ballistic ( Entity source, Entity projectile ) {
        return indirect( source, projectile, AtherysDamageTypes.BALLISTIC );
    }

    public static AtherysIndirectEntityDamageSource piercing ( Entity source, Entity projectile ) {
        return indirect( source, projectile, AtherysDamageTypes.PIERCE );
    }

    // Magic
    public static AtherysDirectEntityDamageSource fire ( Entity source ) {
        return directMagic( source, AtherysDamageTypes.FIRE );
    }

    public static AtherysDirectEntityDamageSource ice ( Entity source ) {
        return directMagic( source, AtherysDamageTypes.ICE );
    }

    public static AtherysDirectEntityDamageSource arcane ( Entity source ) {
        return directMagic( source, AtherysDamageTypes.ARCANE );
    }

    public static AtherysDirectEntityDamageSource shock ( Entity source ) {
        return directMagic( source, AtherysDamageTypes.SHOCK );
    }

    public static AtherysDirectEntityDamageSource nature ( Entity source ) {
        return directMagic( source, AtherysDamageTypes.NATURE );
    }

    public static AtherysDirectEntityDamageSource mental ( Entity source ) {
        return directMagic( source, AtherysDamageTypes.MENTAL );
    }

    public static AtherysDirectEntityDamageSource radiant ( Entity source ) {
        return directMagic( source, AtherysDamageTypes.RADIANT );
    }

    public static AtherysDirectEntityDamageSource necrotic ( Entity source ) {
        return directMagic( source, AtherysDamageTypes.NECROTIC );
    }

    public static AtherysDirectEntityDamageSource blood ( Entity source ) {
        return directMagic( source, AtherysDamageTypes.BLOOD );
    }
}
