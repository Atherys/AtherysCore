package com.atherys.core.damage.sources;

import com.atherys.core.damage.AtherysDamageType;
import org.spongepowered.api.entity.Entity;

public class AtherysDamageSources {

    public static AtherysEntityDamageSourceBuilder melee ( AtherysDamageType type, Entity source ) {
        return AtherysEntityDamageSource.builder().atherysType( type ).entity( source );
    }

    public static AtherysIndirectEntityDamageSourceBuilder ranged ( AtherysDamageType type, Entity source, Entity projectile ) {
        return AtherysIndirectEntityDamageSource.builder().atherysType( type ).entity( source ).proxySource( projectile );
    }

    public static AtherysEntityDamageSourceBuilder magic ( AtherysDamageType type, Entity source ) {
        return AtherysEntityDamageSource.builder().atherysType( type ).magical().entity( source );
    }

}
