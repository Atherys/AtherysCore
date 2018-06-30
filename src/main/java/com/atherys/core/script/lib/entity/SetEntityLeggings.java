package com.atherys.core.script.lib.entity;

import org.spongepowered.api.entity.ArmorEquipable;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.item.inventory.ItemStack;

import java.util.function.BiFunction;

public class SetEntityLeggings implements BiFunction<Entity, ItemStack, Boolean> {
    public Boolean apply(Entity entity, ItemStack leggings){
        if (!(entity instanceof ArmorEquipable)) return false;
        ((ArmorEquipable) entity).setLeggings(leggings);
        return true;
    }
}

