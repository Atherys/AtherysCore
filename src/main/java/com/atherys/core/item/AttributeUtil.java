package com.atherys.core.item;

import com.atherys.core.item.exception.AttributeModifierException;
import com.atherys.core.utils.DataUtils;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataQuery;
import org.spongepowered.api.data.DataView;
import org.spongepowered.api.data.persistence.InvalidDataException;
import org.spongepowered.api.item.inventory.ItemStack;

import java.util.*;

public final class AttributeUtil {

    public static final ItemStack updateAttributeModifiers(ItemStack itemStack, List<AttributeModifier> attributeModifiers) {
        List<DataContainer> modifiers = new ArrayList<>();

        // TODO: When a new attribute modifier is applied to an item, append a description of it to the end of the item's lore
        //      note: Be sure to erase old attribute modifier descriptions and replace with new ones
        attributeModifiers.forEach(modifier -> modifiers.add(modifier.asDataContainer()));

        return DataUtils.writeUnsafeDataToItemStack(itemStack, modifiers, "AttributeModifiers");
    }

    public static final List<AttributeModifier> getItemStackAttributeModifiers(ItemStack itemStack) {
        DataContainer itemContainer = itemStack.toContainer();

        Optional<List<DataView>> listOfContainers = itemContainer.getViewList(DataQuery.of("UnsafeData", "AttributeModifiers"));

        // If no attribute modifiers could be found, return an empty list
        if (!listOfContainers.isPresent()) {
            return new ArrayList<>();
        }

        List<AttributeModifier> modifiers = new ArrayList<>();
        listOfContainers.get().forEach(view -> {
            try {
                modifiers.add(createModifierFromView(view));
            } catch (InvalidDataException e) {
                throw new AttributeModifierException(view);
            }
        });
        return modifiers;
    }

    private static final AttributeModifier createModifierFromView(DataView view) throws InvalidDataException {
        return new AttributeModifier(
                view.getString(DataQuery.of("AttributeName")).orElseThrow(InvalidDataException::new),
                view.getString(DataQuery.of("Slot")).orElseThrow(InvalidDataException::new),
                view.getFloat(DataQuery.of("Amount")).orElseThrow(InvalidDataException::new),
                view.getInt(DataQuery.of("Operation")).orElseThrow(InvalidDataException::new)
        );
    }

//    private static final Text getAttributeModificationText(AttributeModifier attributeModification) {
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
