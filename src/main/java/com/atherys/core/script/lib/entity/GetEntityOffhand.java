package com.atherys.core.script.lib.entity;

import org.spongepowered.api.data.type.HandTypes;
import org.spongepowered.api.entity.ArmorEquipable;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.item.inventory.ItemStack;

import java.util.function.Function;

public class GetEntityOffhand implements Function<Entity, ItemStack> {
    public ItemStack apply(Entity entity){
        if (!(entity instanceof ArmorEquipable)) return null;
        return ((ArmorEquipable) entity).getItemInHand(HandTypes.OFF_HAND).orElse(null);
    }
}
