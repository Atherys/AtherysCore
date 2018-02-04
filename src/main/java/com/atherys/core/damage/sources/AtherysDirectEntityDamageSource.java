package com.atherys.core.damage.sources;

import com.atherys.core.damage.AtherysDamageType;
import org.spongepowered.api.event.cause.entity.damage.source.common.AbstractEntityDamageSource;

public final class AtherysDirectEntityDamageSource extends AbstractEntityDamageSource implements AtherysEntityDamageSource {

    public static class Builder extends AbstractEntityDamageSourceBuilder<AtherysDirectEntityDamageSource, Builder> {

        private AtherysDamageType type;

        public Builder atherysType ( AtherysDamageType type ) {
            this.type = type;
            return this;
        }

        @Override
        public AtherysDirectEntityDamageSource build() throws IllegalStateException {
            return new AtherysDirectEntityDamageSource( this );
        }
    }

    private AtherysDamageType type;

    @Override
    public AtherysDamageType getAltType() {
        return type;
    }

    protected AtherysDirectEntityDamageSource(Builder builder) {
        super(builder);
        this.type = builder.type;
    }
}
