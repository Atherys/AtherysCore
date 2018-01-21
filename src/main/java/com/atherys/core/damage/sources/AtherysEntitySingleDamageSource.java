package com.atherys.core.damage.sources;

import com.atherys.core.damage.AtherysDamageType;
import org.spongepowered.api.event.cause.entity.damage.DamageType;
import org.spongepowered.api.event.cause.entity.damage.source.common.AbstractEntityDamageSource;

public final class AtherysEntitySingleDamageSource extends AbstractEntityDamageSource implements AtherysDamageSource {

    public static class Builder extends AbstractEntityDamageSourceBuilder<AtherysEntitySingleDamageSource, Builder> {

        @Override
        @Deprecated
        public Builder type ( DamageType type ) {
            super.type( type );
            return this;
        }

        public Builder type ( AtherysDamageType type ) {
            super.type( type );
            return this;
        }

        @Override
        public AtherysEntitySingleDamageSource build() throws IllegalStateException {
            return new AtherysEntitySingleDamageSource( this );
        }
    }

    protected AtherysEntitySingleDamageSource(Builder builder) {
        super(builder);
    }
}
