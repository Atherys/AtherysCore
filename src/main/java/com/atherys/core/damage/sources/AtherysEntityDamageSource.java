package com.atherys.core.damage.sources;

import com.atherys.core.damage.AtherysDamageType;
import org.spongepowered.api.event.cause.entity.damage.DamageType;
import org.spongepowered.api.event.cause.entity.damage.source.common.AbstractEntityDamageSource;

public final class AtherysEntityDamageSource extends AbstractEntityDamageSource {

    public static class Builder extends AbstractEntityDamageSourceBuilder<AtherysEntityDamageSource, Builder> {

        @Override
        @Deprecated
        public Builder type ( DamageType type ) {
            return this;
        }

        public Builder type ( AtherysDamageType type ) {
            super.type( type );
            return this;
        }

        @Override
        public AtherysEntityDamageSource build() throws IllegalStateException {
            return new AtherysEntityDamageSource( this );
        }
    }

    protected AtherysEntityDamageSource(Builder builder) {
        super(builder);
    }
}
