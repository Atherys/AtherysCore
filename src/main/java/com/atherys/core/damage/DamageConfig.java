package com.atherys.core.damage;

import com.atherys.core.damage.sources.AtherysDamageSource;
import com.atherys.core.damage.sources.AtherysDamageSources;
import com.atherys.core.utils.InventoryUtils;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.event.cause.entity.damage.source.EntityDamageSource;
import org.spongepowered.api.event.cause.entity.damage.source.IndirectEntityDamageSource;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@ConfigSerializable
public class DamageConfig {

    @Setting( "damage_per_type" )
    public Map<ItemType,AtherysDamageType> damageMap = new HashMap<>();

    public AtherysDamageSource getSource ( EntityDamageSource originalSource ) {

        if ( originalSource instanceof IndirectEntityDamageSource ) {
            Entity source = ((IndirectEntityDamageSource) originalSource).getIndirectSource();

            Entity projectile = originalSource.getSource();

            Optional<ItemStack> stack = InventoryUtils.getMainHand( source );
            if ( stack.isPresent() ) {
                return AtherysDamageSources.indirect( source, projectile, damageMap.getOrDefault( stack.get().getType(), AtherysDamageTypes.UNARMED ) );
            } else {
                return AtherysDamageSources.indirect( source, projectile, AtherysDamageTypes.UNARMED );
            }

        } else {
            Entity source = originalSource.getSource();

            Optional<ItemStack> stack = InventoryUtils.getMainHand( source );
            if ( stack.isPresent() ) {
                return AtherysDamageSources.direct( source, damageMap.getOrDefault( stack.get().getType(), AtherysDamageTypes.UNARMED ) );
            } else {
                return AtherysDamageSources.direct( source, AtherysDamageTypes.UNARMED );
            }
        }

    }
}
