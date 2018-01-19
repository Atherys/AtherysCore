package com.atherys.core.damage.listeners;

import com.atherys.core.AtherysCore;
import com.atherys.core.damage.sources.AtherysEntityMultiDamageSource;
import com.atherys.core.damage.sources.AtherysEntitySingleDamageSource;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.event.entity.DamageEntityEvent;
import org.spongepowered.api.event.filter.cause.First;

public class DamageListeners {

    @Listener ( order = Order.FIRST )
    public void onDirectMultiDamage ( DamageEntityEvent event, @First AtherysEntityMultiDamageSource source ) {
        event.setCancelled(true);
        if ( event.getCause().containsType(AtherysEntitySingleDamageSource.class) ) return;
        AtherysCore.getInstance().getLogger().info( "Multi Damage Source Detected" );
        source.getDamageDistribution().forEach( (k,v) -> event.getTargetEntity().damage( event.getBaseDamage() * v, k )  );
    }

}
