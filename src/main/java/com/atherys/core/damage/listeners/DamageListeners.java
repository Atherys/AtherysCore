package com.atherys.core.damage.listeners;

import com.atherys.core.AtherysCore;
import com.atherys.core.damage.sources.AtherysEntityMultiDamageSource;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.event.entity.DamageEntityEvent;
import org.spongepowered.api.event.filter.cause.First;

public class DamageListeners {

    @Listener ( order = Order.FIRST )
    public void onDirectMultiDamage ( DamageEntityEvent event, @First AtherysEntityMultiDamageSource source ) {
        AtherysCore.getInstance().getLogger().info( "Multi Damage Source Detected" );
        event.setCancelled( true );
        Sponge.getCauseStackManager().popCause();
        source.getDamageDistribution().forEach( (k,v) -> event.getTargetEntity().damage( event.getBaseDamage() * v, k )  );
    }

}
