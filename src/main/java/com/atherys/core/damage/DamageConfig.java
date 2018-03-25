package com.atherys.core.damage;

import com.atherys.core.damage.sources.AtherysDamageSources;
import com.atherys.core.utils.InventoryUtils;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.event.cause.entity.damage.source.EntityDamageSource;
import org.spongepowered.api.event.cause.entity.damage.source.IndirectEntityDamageSource;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * This class is responsible for retrieving what sort of damage type different item types ought to be doing.
 * The map itself is saved in a file config. See: {@link com.atherys.core.utils.PluginConfig}
 */
@ConfigSerializable
public class DamageConfig {

    @Setting( "damage_per_type" )
    private Map<ItemType, AtherysDamageType> damageMap = new HashMap<>();
    {
        damageMap.put( ItemTypes.WOODEN_SWORD, AtherysDamageTypes.SLASH );
    }

    public AtherysDamageType getItemDamageType ( ItemType type ) {
        return damageMap.getOrDefault( type, AtherysDamageTypes.UNARMED );
    }

    public AtherysDamageType getDamageType ( EntityDamageSource originalSource ) {
        Entity source;

        if ( originalSource instanceof IndirectEntityDamageSource ) {
            source = ( ( IndirectEntityDamageSource ) originalSource ).getIndirectSource();
        } else {
            source = originalSource.getSource();
        }

        Optional<ItemStack> stack = InventoryUtils.getMainHand( source );
        if ( stack.isPresent() ) {
            return getItemDamageType( stack.get().getType() );
        } else {
            return AtherysDamageTypes.UNARMED;
        }
    }

    public EntityDamageSource getDamageSource ( EntityDamageSource source ) {
        AtherysDamageType type = getDamageType( source );

        if ( source instanceof IndirectEntityDamageSource ) {
            Entity entitySource = ( ( IndirectEntityDamageSource ) source ).getIndirectSource();
            Entity projectile = source.getSource();

            return AtherysDamageSources.ranged( type, entitySource, projectile ).build();
        } else {
            Entity entitySource = source.getSource();

            return AtherysDamageSources.melee( type, entitySource ).build();
        }

    }
}
