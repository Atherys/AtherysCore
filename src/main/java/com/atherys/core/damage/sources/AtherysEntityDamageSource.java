package com.atherys.core.damage.sources;

import com.atherys.core.damage.AtherysDamageType;
import org.spongepowered.api.event.cause.entity.damage.source.common.AbstractEntityDamageSource;

public class AtherysEntityDamageSource extends AbstractEntityDamageSource {

    private AtherysDamageType type;

    protected AtherysEntityDamageSource ( AbstractEntityDamageSourceBuilder<?, ?> builder ) {
        super( builder );
    }

    protected AtherysEntityDamageSource ( AbstractEntityDamageSourceBuilder<?, ?> builder, AtherysDamageType type ) {
        super( builder );
        this.type = type;
    }

    public static AtherysEntityDamageSourceBuilder builder () {
        return new AtherysEntityDamageSourceBuilder();
    }

    public AtherysDamageType getAtherysType () {
        return type;
    }
}
