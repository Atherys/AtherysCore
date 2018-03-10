package com.atherys.core.damage.sources;

import com.atherys.core.damage.AtherysDamageType;
import org.spongepowered.api.event.cause.entity.damage.source.common.AbstractIndirectEntityDamageSource;

/**
 * The A'therys custom implementation of Sponge's {@link AbstractIndirectEntityDamageSource}
 */
public final class AtherysIndirectEntityDamageSource extends AbstractIndirectEntityDamageSource implements AtherysEntityDamageSource {

    public static class Builder extends AbstractIndirectEntityDamageSourceBuilder<AtherysIndirectEntityDamageSource, Builder> {

        private AtherysDamageType type;

        public Builder atherysType ( AtherysDamageType type ) {
            this.type = type;
            return this;
        }

        @Override
        public AtherysIndirectEntityDamageSource build() throws IllegalStateException {
            return new AtherysIndirectEntityDamageSource(this);
        }
    }

    private AtherysDamageType type;

    @Override
    public AtherysDamageType getAltType() {
        return type;
    }

    protected AtherysIndirectEntityDamageSource( Builder builder ) {
        super(builder);
        this.type = builder.type;
    }
}
