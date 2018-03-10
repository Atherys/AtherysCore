package com.atherys.core.damage.sources;

import com.atherys.core.damage.AtherysDamageType;
import com.atherys.core.damage.AtherysDamageTypes;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.event.cause.entity.damage.DamageTypes;
import org.spongepowered.api.event.cause.entity.damage.source.DamageSource;

/**
 * A factory class for getting {@link AtherysDamageSource}s for different {@link AtherysDamageType}
 */
public final class AtherysDamageSources {

    /**
     * Creates a single direct damage source builder
     * @param source the entity source of this damage
     * @return the builder
     */
    public static AtherysDirectEntityDamageSource.Builder directBuilder ( Entity source ) {
        return new AtherysDirectEntityDamageSource.Builder().type( DamageTypes.CUSTOM ).entity( source );
    }

    /**
     * Creates a single indirect damage source builder
     * @param source the entity source of this damage
     * @param proxy the proxy entity ( See: {@link org.spongepowered.api.entity.projectile.Projectile} )
     * @return The builder
     */
    public static AtherysIndirectEntityDamageSource.Builder indirectBuilder ( Entity source, Entity proxy ) {
        return new AtherysIndirectEntityDamageSource.Builder().type( DamageTypes.CUSTOM ).entity( source ).proxySource( proxy );
    }

    /**
     * A direct {@link AtherysDamageSource} with the given {@link AtherysDamageType}
     * @param source The entity source of the damage
     * @param type The custom damage type
     * @return the source
     */
    public static AtherysDirectEntityDamageSource direct ( Entity source, AtherysDamageType type) {
        return directBuilder( source ).type( type.getPrimitive() ).atherysType( type ).build();
    }

    /**
     * A direct magical {@link AtherysDamageSource} with the given {@link AtherysDamageType} ( See: {@link DamageSource.DamageSourceBuilder#magical()}
     * @param source The entity source of the damage
     * @param type The custom damage type
     * @return the source
     */
    public static AtherysDirectEntityDamageSource directMagic ( Entity source, AtherysDamageType type ) {
        return directBuilder( source ).type( type.getPrimitive() ).atherysType( type ).magical().build();
    }

    /**
     * An indirect {@link AtherysDamageSource} with the given {@link AtherysDamageType}
     * @param source The entity source of the damage
     * @param type The custom damage type
     * @param proxy The proxy entity for this damage
     * @return the source
     */
    public static AtherysIndirectEntityDamageSource indirect ( Entity source, Entity proxy, AtherysDamageType type) {
        return indirectBuilder( source, proxy ).type( type.getPrimitive() ).atherysType( type ).build();
    }

    // Melee

    /**
     * An {@link AtherysDirectEntityDamageSource} of type {@link AtherysDamageTypes#BLUNT}
     * @param source The entity source of this damage
     * @return the DamageSource
     */
    public static AtherysDirectEntityDamageSource blunt ( Entity source ) {
        return direct( source, AtherysDamageTypes.BLUNT );
    }

    /**
     * An {@link AtherysDirectEntityDamageSource} of type {@link AtherysDamageTypes#SLASH}
     * @param source The entity source of this damage
     * @return the DamageSource
     */
    public static AtherysDirectEntityDamageSource slash ( Entity source ) {
        return direct( source, AtherysDamageTypes.SLASH );
    }

    /**
     * An {@link AtherysDirectEntityDamageSource} of type {@link AtherysDamageTypes#STAB}
     * @param source The entity source of this damage
     * @return the DamageSource
     */
    public static AtherysDirectEntityDamageSource stab ( Entity source ) {
        return direct( source, AtherysDamageTypes.STAB );
    }

    /**
     * An {@link AtherysDirectEntityDamageSource} of type {@link AtherysDamageTypes#UNARMED}
     * @param source The entity source of this damage
     * @return the DamageSource
     */
    public static AtherysDirectEntityDamageSource unarmed ( Entity source ) {
        return direct( source, AtherysDamageTypes.UNARMED );
    }

    // Ranged

    /**
     * An {@link AtherysIndirectEntityDamageSource} of type {@link AtherysDamageTypes#BALLISTIC}
     * @param source The entity source of this damage
     * @param projectile the proxy entity for this damage
     * @return the DamageSource
     */
    public static AtherysIndirectEntityDamageSource ballistic ( Entity source, Entity projectile ) {
        return indirect( source, projectile, AtherysDamageTypes.BALLISTIC );
    }

    /**
     * An {@link AtherysIndirectEntityDamageSource} of type {@link AtherysDamageTypes#PIERCE}
     * @param source The entity source of this damage
     * @param projectile the proxy entity for this damage
     * @return the DamageSource
     */
    public static AtherysIndirectEntityDamageSource piercing ( Entity source, Entity projectile ) {
        return indirect( source, projectile, AtherysDamageTypes.PIERCE );
    }

    // Magic

    /**
     * ( See: {@link #directMagic(Entity, AtherysDamageType)} ) A magical {@link AtherysDirectEntityDamageSource} of type {@link AtherysDamageTypes#FIRE}
     * @param source The entity source of this damage
     * @return the DamageSource
     */
    public static AtherysDirectEntityDamageSource fire ( Entity source ) {
        return directMagic( source, AtherysDamageTypes.FIRE );
    }

    /**
     * ( See: {@link #directMagic(Entity, AtherysDamageType)} ) A magical {@link AtherysDirectEntityDamageSource} of type {@link AtherysDamageTypes#ICE}
     * @param source The entity source of this damage
     * @return the DamageSource
     */
    public static AtherysDirectEntityDamageSource ice ( Entity source ) {
        return directMagic( source, AtherysDamageTypes.ICE );
    }

    /**
     * ( See: {@link #directMagic(Entity, AtherysDamageType)} ) A magical {@link AtherysDirectEntityDamageSource} of type {@link AtherysDamageTypes#ARCANE}
     * @param source The entity source of this damage
     * @return the DamageSource
     */
    public static AtherysDirectEntityDamageSource arcane ( Entity source ) {
        return directMagic( source, AtherysDamageTypes.ARCANE );
    }

    /**
     * ( See: {@link #directMagic(Entity, AtherysDamageType)} ) A magical {@link AtherysDirectEntityDamageSource} of type {@link AtherysDamageTypes#SHOCK}
     * @param source The entity source of this damage
     * @return the DamageSource
     */
    public static AtherysDirectEntityDamageSource shock ( Entity source ) {
        return directMagic( source, AtherysDamageTypes.SHOCK );
    }

    /**
     * ( See: {@link #directMagic(Entity, AtherysDamageType)} ) A magical {@link AtherysDirectEntityDamageSource} of type {@link AtherysDamageTypes#NATURE}
     * @param source The entity source of this damage
     * @return the DamageSource
     */
    public static AtherysDirectEntityDamageSource nature ( Entity source ) {
        return directMagic( source, AtherysDamageTypes.NATURE );
    }

    /**
     * ( See: {@link #directMagic(Entity, AtherysDamageType)} ) A magical {@link AtherysDirectEntityDamageSource} of type {@link AtherysDamageTypes#MENTAL}
     * @param source The entity source of this damage
     * @return the DamageSource
     */
    public static AtherysDirectEntityDamageSource mental ( Entity source ) {
        return directMagic( source, AtherysDamageTypes.MENTAL );
    }

    /**
     * ( See: {@link #directMagic(Entity, AtherysDamageType)} ) A magical {@link AtherysDirectEntityDamageSource} of type {@link AtherysDamageTypes#RADIANT}
     * @param source The entity source of this damage
     * @return the DamageSource
     */
    public static AtherysDirectEntityDamageSource radiant ( Entity source ) {
        return directMagic( source, AtherysDamageTypes.RADIANT );
    }

    /**
     * ( See: {@link #directMagic(Entity, AtherysDamageType)} ) A magical {@link AtherysDirectEntityDamageSource} of type {@link AtherysDamageTypes#NECROTIC}
     * @param source The entity source of this damage
     * @return the DamageSource
     */
    public static AtherysDirectEntityDamageSource necrotic ( Entity source ) {
        return directMagic( source, AtherysDamageTypes.NECROTIC );
    }

    /**
     * ( See: {@link #directMagic(Entity, AtherysDamageType)} ) A magical {@link AtherysDirectEntityDamageSource} of type {@link AtherysDamageTypes#BLOOD}
     * @param source The entity source of this damage
     * @return the DamageSource
     */
    public static AtherysDirectEntityDamageSource blood ( Entity source ) {
        return directMagic( source, AtherysDamageTypes.BLOOD );
    }
}
