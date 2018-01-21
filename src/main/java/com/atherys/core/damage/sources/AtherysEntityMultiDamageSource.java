package com.atherys.core.damage.sources;

import com.atherys.core.damage.AtherysDamageTypes;
import com.atherys.core.utils.MathUtils;
import org.spongepowered.api.event.cause.entity.damage.DamageType;
import org.spongepowered.api.event.cause.entity.damage.source.common.AbstractEntityDamageSource;

import java.util.HashMap;
import java.util.Map;

public final class AtherysEntityMultiDamageSource extends AbstractEntityDamageSource implements AtherysDamageSource {

    private Map<AtherysEntitySingleDamageSource,Float> distro;

    public static class Builder extends AbstractEntityDamageSourceBuilder<AtherysEntityMultiDamageSource, Builder> {

        private Map<AtherysEntitySingleDamageSource,Float> distro = new HashMap<>();

        @Override
        @Deprecated
        public Builder type ( DamageType type ) {
            super.type( type );
            return this;
        }

        public Builder addType ( AtherysEntitySingleDamageSource source, Float percentage ) {
            distro.put( source, MathUtils.clamp( percentage, 0.0f, 1.0f ) );
            return this;
        }

        public Builder distribution ( Map<AtherysEntitySingleDamageSource,Float> damageDistro ) {
            super.type(AtherysDamageTypes.UNARMED);
            this.distro = damageDistro;
            return this;
        }

        @Override
        public AtherysEntityMultiDamageSource build() throws IllegalStateException {
            return new AtherysEntityMultiDamageSource( this );
        }
    }

    protected AtherysEntityMultiDamageSource(Builder builder) {
        super(builder);
        this.distro = builder.distro;
    }

    public Map<AtherysEntitySingleDamageSource, Float> getDamageDistribution() {
        return distro;
    }

}
