package com.atherys.core.script.lib.entity;

import org.spongepowered.api.entity.ArmorEquipable;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.Item;
import org.spongepowered.api.item.inventory.ItemStack;

import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Function;

public class GetEntityBoots implements Function<Entity, ItemStack> {
    public ItemStack apply(Entity entity){
        if (!(entity instanceof ArmorEquipable)){
            return null;
        }
        return ((ArmorEquipable) entity).getBoots().orElse(null);
    }
}
