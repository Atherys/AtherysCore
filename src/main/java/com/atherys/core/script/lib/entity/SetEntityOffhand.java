package com.atherys.core.script.lib.entity;

import org.spongepowered.api.data.type.HandTypes;
import org.spongepowered.api.entity.ArmorEquipable;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.item.inventory.ItemStack;

import java.util.function.BiFunction;

public class SetEntityOffhand implements BiFunction<Entity, ItemStack, Boolean> {
    public Boolean apply(Entity entity, ItemStack item){
        if (!(entity instanceof ArmorEquipable)) return false;
        ((ArmorEquipable) entity).setItemInHand(HandTypes.OFF_HAND, item);
        return true;
    }
}
