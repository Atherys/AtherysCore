package com.atherys.core.damage.sources;

import com.atherys.core.damage.AtherysDamageType;
import org.spongepowered.api.event.cause.entity.damage.DamageType;
import org.spongepowered.api.event.cause.entity.damage.source.common.AbstractEntityDamageSource;

public final class AtherysDirectEntityDamageSource extends AbstractEntityDamageSource implements AtherysEntityDamageSource {

    public static class Builder extends AbstractEntityDamageSourceBuilder<AtherysDirectEntityDamageSource, Builder> {

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
        public AtherysDirectEntityDamageSource build() throws IllegalStateException {
            return new AtherysDirectEntityDamageSource( this );
        }
    }

    protected AtherysDirectEntityDamageSource(Builder builder) {
        super(builder);
    }
}
