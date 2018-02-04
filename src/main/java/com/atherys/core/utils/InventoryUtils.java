package com.atherys.core.utils;

import org.spongepowered.api.entity.ArmorEquipable;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.equipment.EquipmentType;
import org.spongepowered.api.item.inventory.equipment.EquipmentTypes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class InventoryUtils {

    public static final List<EquipmentType> EQUIPMENT_SLOTS = Arrays.asList(
            EquipmentTypes.HEADWEAR,
            EquipmentTypes.CHESTPLATE,
            EquipmentTypes.LEGGINGS,
            EquipmentTypes.BOOTS,
            EquipmentTypes.MAIN_HAND,
            EquipmentTypes.OFF_HAND
    );

    public static Optional<List<ItemStack>> getEquippedItems ( Entity entity ) {
        if ( !(entity instanceof ArmorEquipable) ) return Optional.empty();
        ArmorEquipable equipable = (ArmorEquipable) entity;

        List<ItemStack> equippedItems = new ArrayList<>();

        EQUIPMENT_SLOTS.forEach( type -> equipable.getEquipped(type).ifPresent( equippedItems::add ) );

        return Optional.of(equippedItems);
    }

    public static Optional<ItemStack> getMainHand( Entity entity ) {
        return getEquipment(entity, EquipmentTypes.MAIN_HAND);
    }

    public static Optional<ItemStack> getOffHand( Entity entity ) {
        return getEquipment(entity, EquipmentTypes.OFF_HAND);
    }

    public static Optional<ItemStack> getEquipment( Entity entity, EquipmentType type ) {
        if ( !(entity instanceof ArmorEquipable) ) return Optional.empty();
        ArmorEquipable equipable = (ArmorEquipable) entity;

        return equipable.getEquipped(type);
    }

}
