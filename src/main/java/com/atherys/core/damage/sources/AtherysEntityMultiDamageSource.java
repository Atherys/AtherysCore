package com.atherys.core.damage.sources;

import com.atherys.core.damage.AtherysDamageType;
import com.atherys.core.damage.AtherysDamageTypes;
import com.atherys.core.utils.MathUtils;
import org.spongepowered.api.event.cause.entity.damage.DamageType;
import org.spongepowered.api.event.cause.entity.damage.source.common.AbstractEntityDamageSource;

import java.util.HashMap;
import java.util.Map;

public final class AtherysEntityMultiDamageSource extends AbstractEntityDamageSource {

    private Map<AtherysDamageType,Float> distro;

    public static class Builder extends AbstractEntityDamageSourceBuilder<AtherysEntityMultiDamageSource, Builder> {

        private Map<AtherysDamageType,Float> distro = new HashMap<>();

        @Override
        @Deprecated
        public Builder type ( DamageType type ) {
            super.type( type );
            return this;
        }

        public Builder addType ( AtherysDamageType type, Float percentage ) {
            distro.put( type, MathUtils.clamp( percentage, 0.0f, 1.0f ) );
            return this;
        }

        public Builder distribution ( Map<AtherysDamageType,Float> damageDistro ) {
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

    public Map<AtherysDamageType, Float> getDamageDistribution() {
        return distro;
    }
}
