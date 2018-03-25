package com.atherys.core.damage.sources;

import com.atherys.core.damage.AtherysDamageType;
import org.spongepowered.api.event.cause.entity.damage.source.common.AbstractEntityDamageSource;
import org.spongepowered.api.event.cause.entity.damage.source.common.AbstractIndirectEntityDamageSource;

public class AtherysIndirectEntityDamageSource extends AbstractIndirectEntityDamageSource {

    private AtherysDamageType type;

    protected AtherysIndirectEntityDamageSource ( AtherysIndirectEntityDamageSource.AbstractIndirectEntityDamageSourceBuilder<?, ?> builder ) {
        super( builder );
    }

    protected AtherysIndirectEntityDamageSource ( AtherysIndirectEntityDamageSource.AbstractIndirectEntityDamageSourceBuilder<?, ?> builder, AtherysDamageType type ) {
        super( builder );
        this.type = type;
    }

    public static AtherysIndirectEntityDamageSourceBuilder builder () {
        return new AtherysIndirectEntityDamageSourceBuilder();
    }

    public AtherysDamageType getAtherysType () {
        return type;
    }
}
