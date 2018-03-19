package com.atherys.core.damage.sources;

import com.atherys.core.damage.AtherysDamageType;
import org.spongepowered.api.event.cause.entity.damage.source.DamageSource;

/**
 * A'therys replacement interface for Sponge's {@link DamageSource}
 */
public interface AtherysDamageSource extends DamageSource {

    /**
     * The {@link AtherysDamageType} contained within this DamageSource.
     *
     * @return
     */
    AtherysDamageType getAltType();

}
