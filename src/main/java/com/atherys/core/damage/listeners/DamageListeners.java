package com.atherys.core.damage.listeners;

import com.atherys.core.AtherysCore;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.CauseStackManager;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.event.cause.entity.damage.source.EntityDamageSource;
import org.spongepowered.api.event.entity.DamageEntityEvent;
import org.spongepowered.api.event.filter.cause.Root;

public class DamageListeners {

    @Listener( order = Order.FIRST )
    public void onSingleDamage( DamageEntityEvent event, @Root EntityDamageSource source ) {
        try ( CauseStackManager.StackFrame frame = Sponge.getCauseStackManager().pushCauseFrame() ) {
            frame.pushCause( AtherysCore.getConfig().DAMAGE.getDamageType( source ) );
        }
    }

}
