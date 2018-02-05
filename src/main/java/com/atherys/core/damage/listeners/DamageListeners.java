package com.atherys.core.damage.listeners;

import com.atherys.core.AtherysCore;
import com.atherys.core.damage.sources.AtherysDamageSource;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.event.cause.entity.damage.source.EntityDamageSource;
import org.spongepowered.api.event.entity.DamageEntityEvent;
import org.spongepowered.api.event.filter.cause.Root;

public class DamageListeners {

    @Listener ( order = Order.FIRST )
    public void onSingleDamage ( DamageEntityEvent event, @Root EntityDamageSource source ) {
        if ( event.getCause().containsType(AtherysDamageSource.class) ) return;

        event.setCancelled( true );
        AtherysDamageSource newSource = AtherysCore.getConfig().DAMAGE.getSource ( source );
        event.getTargetEntity().damage( event.getBaseDamage(), newSource );
        AtherysCore.getInstance().getLogger().info("Dealing " + event.getFinalDamage() + " " + newSource.getAltType().getName() + " damage");
    }

}
