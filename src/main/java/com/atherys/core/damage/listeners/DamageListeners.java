package com.atherys.core.damage.listeners;

import com.atherys.core.damage.sources.AtherysDamageSource;
import com.atherys.core.damage.sources.AtherysEntityMultiDamageSource;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.event.entity.DamageEntityEvent;
import org.spongepowered.api.event.filter.cause.First;

public class DamageListeners {

    @Listener ( order = Order.FIRST )
    public void onDirectMultiDamage ( DamageEntityEvent event, @First AtherysDamageSource source ) {
        if ( source instanceof AtherysEntityMultiDamageSource ) {
            event.setCancelled(true);
            ((AtherysEntityMultiDamageSource)source).getDamageDistribution().forEach((k, v) -> event.getTargetEntity().damage(event.getBaseDamage() * v, k));
        }
    }

}
