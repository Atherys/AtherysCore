package com.atherys.core.item;

import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataQuery;
import org.spongepowered.api.item.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class AttributeUtils {

    public static final ItemStack replaceAttributeModifiers(ItemStack itemStack, List<AttributeModification> attributeModifications) {
        List<DataContainer> modifiers = new ArrayList<>();

        // TODO: When a new attribute modifier is applied to an item, append a description of it to the end of the item's lore
        //      note: Be sure to erase old attribute modifier descriptions and replace with new ones
        attributeModifications.forEach(modifier -> modifiers.add(modifier.asDataContainer()));

        DataContainer dataContainer = itemStack.toContainer();
        dataContainer.set(DataQuery.of("UnsafeData", "AttributeModifiers"), modifiers);
        return itemStack;
    }

//    private static final Text getAttributeModificationText(AttributeModification attributeModification) {
//        Text.Builder attributeText = Text.builder();
//
//        if (attributeModification.getOperation() == AttributeOperations.ADDITIVE) {
//            attributeText.append()
//        } else if (attributeModification.getOperation() == AttributeOperations.MULTIPLICATIVE_ADDITIVE) {
//
//        } else {
//
//        }
//
//        return attributeText.build();
//    }

}
