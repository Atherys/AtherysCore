package com.atherys.core.script.lib.item;

import com.atherys.core.script.lib.ScriptLibrary;
import org.spongepowered.api.item.inventory.ItemStack;

import javax.script.ScriptEngine;

/**
 * Functions responsible for the creation and manipulation of {@link ItemStack )s
 */
public final class ItemStackLibrary implements ScriptLibrary {

    @Override
    public void registerObjects(ScriptEngine engine) {
        engine.put("createItemStack", new CreateItemStack());

        // Getters
        engine.put("getItemStackEnchants", new GetItemStackEnchants());
        engine.put("getItemStackDisplayName", new GetItemStackDisplayName());
        engine.put("getItemStackLore", new GetItemStackLore());

        // Setters
        engine.put("setItemStackEnchants", new SetItemStackEnchants());
        engine.put("setItemStackDisplayName", new SetItemStackDisplayName());
        engine.put("setItemStackLore", new SetItemStackLore());

        // Utilities
        engine.put("isItemStack", new IsItemStack());
        engine.put("compareItemStacks", new CompareItemStacks());
        engine.put("dropItemStackOnGround", new DropItemStackOnGround());
    }

}
