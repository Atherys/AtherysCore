package com.atherys.core.damage.listeners;

import com.atherys.core.damage.sources.AtherysEntityMultiDamageSource;
import com.atherys.core.damage.sources.AtherysEntitySingleDamageSource;
import com.atherys.core.damage.sources.AtherysIndirectEntityDamageSource;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.event.entity.DamageEntityEvent;
import org.spongepowered.api.event.filter.cause.First;

public class DamageListeners {

    @Listener ( order = Order.FIRST )
    public void onDirectMultiDamage ( DamageEntityEvent event, @First AtherysEntityMultiDamageSource source ) {
        source.getDamageDistribution().forEach( (k,v) -> event.getTargetEntity().damage( event.getBaseDamage() * v, k.asSource( source.getSource() ) ));
        event.setBaseDamage( 0.0 );
    }

    @Listener ( order = Order.FIRST )
    public void onDirectSingleDamage ( DamageEntityEvent event, @First AtherysEntitySingleDamageSource source ) {

    }

    @Listener ( order = Order.FIRST )
    public void onIndirectDamage ( DamageEntityEvent event, @First AtherysIndirectEntityDamageSource source ) {

    }

}
