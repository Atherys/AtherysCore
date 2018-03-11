package com.atherys.core.damage.sources;

import org.spongepowered.api.event.cause.entity.damage.source.EntityDamageSource;

/**
 * An interface combining {@link AtherysDamageSource} and {@link EntityDamageSource}
 */
public interface AtherysEntityDamageSource extends AtherysDamageSource, EntityDamageSource {

}
