package com.atherys.core.damage.sources;

import org.spongepowered.api.event.cause.entity.damage.source.common.AbstractIndirectEntityDamageSource;

public final class AtherysIndirectEntityDamageSource extends AbstractIndirectEntityDamageSource {

    public static class Builder extends AbstractIndirectEntityDamageSourceBuilder<AbstractIndirectEntityDamageSource, Builder> {

        @Override
        public AtherysIndirectEntityDamageSource build() throws IllegalStateException {
            return new AtherysIndirectEntityDamageSource(this);
        }
    }

    protected AtherysIndirectEntityDamageSource( Builder builder ) {
        super(builder);
    }
}
