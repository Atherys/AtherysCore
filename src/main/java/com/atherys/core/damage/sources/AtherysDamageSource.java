package com.atherys.core.damage.sources;

import com.atherys.core.damage.AtherysDamageType;
import org.spongepowered.api.event.cause.entity.damage.source.DamageSource;

public interface AtherysDamageSource extends DamageSource {

    AtherysDamageType getAltType();

}
