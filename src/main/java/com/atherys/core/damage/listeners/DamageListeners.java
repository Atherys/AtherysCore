package com.atherys.core.damage.listeners;

import com.atherys.core.AtherysCore;
import com.atherys.core.damage.sources.AtherysEntityMultiDamageSource;
import com.atherys.core.damage.sources.AtherysEntitySingleDamageSource;
import com.atherys.core.damage.sources.AtherysIndirectEntityDamageSource;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.event.cause.entity.damage.source.DamageSource;
import org.spongepowered.api.event.entity.DamageEntityEvent;
import org.spongepowered.api.event.filter.cause.Last;

public class DamageListeners {

    @Listener ( order = Order.FIRST )
    public void onDirectMultiDamage ( DamageEntityEvent event, @Last AtherysEntityMultiDamageSource source ) {
        AtherysCore.getInstance().getLogger().info( source.getType().getName() + " detected." );
        if ( event.getCause().containsType( AtherysEntitySingleDamageSource.class  ) ||
             event.getCause().containsType( AtherysIndirectEntityDamageSource.class) ) return;
        event.setCancelled(true);
        source.getDamageDistribution().forEach( (k, v) -> event.getTargetEntity().damage( event.getBaseDamage() * v, k) );
    }

    @Listener ( order = Order.FIRST )
    public void test ( DamageEntityEvent event, @Last DamageSource source ) {

    }

}
