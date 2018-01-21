package com.atherys.core.damage.sources;

import com.atherys.core.damage.AtherysDamageTypes;
import com.atherys.core.utils.MathUtils;
import org.spongepowered.api.event.cause.entity.damage.DamageType;
import org.spongepowered.api.event.cause.entity.damage.source.common.AbstractEntityDamageSource;

import java.util.HashMap;
import java.util.Map;

public final class AtherysEntityMultiDamageSource extends AbstractEntityDamageSource implements AtherysDamageSource {

    private Map<AtherysDamageSource,Float> distro;

    public static class Builder extends AbstractEntityDamageSourceBuilder<AtherysEntityMultiDamageSource, Builder> {

        private Map<AtherysDamageSource,Float> distro = new HashMap<>();

        @Override
        @Deprecated
        public Builder type ( DamageType type ) {
            super.type( type );
            return this;
        }

        /**
         * Adds another type of damage to be inflicted, along with the percentage amount of damage it should inflict based on original base damage.
         * @param source the damage source
         * @param percentage the percentage
         * @return the builder instance
         */
        public Builder addType ( AtherysDamageSource source, Float percentage ) {
            distro.put( source, MathUtils.clamp( percentage, 0.0f, 1.0f ) );
            return this;
        }

        public Builder distribution ( Map<AtherysDamageSource,Float> damageDistro ) {
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

    public Map<AtherysDamageSource, Float> getDamageDistribution() {
        return distro;
    }

}
