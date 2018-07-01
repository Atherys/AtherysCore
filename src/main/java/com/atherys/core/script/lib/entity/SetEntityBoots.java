package com.atherys.core.script.lib.entity;

import org.spongepowered.api.entity.ArmorEquipable;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.item.inventory.ItemStack;

import java.util.function.BiFunction;

public class SetEntityBoots implements BiFunction<Entity, ItemStack, Boolean> {
    public Boolean apply(Entity entity, ItemStack boots){
        if (!(entity instanceof ArmorEquipable)) return false;
        ((ArmorEquipable) entity).setBoots(boots);
        return true;
    }
}
