package com.atherys.core.item.exception;

import com.atherys.core.utils.DataUtils;
import org.spongepowered.api.data.DataView;

public class AttributeModifierException extends RuntimeException {
    public AttributeModifierException(String message, Throwable e) {
        super(message, e);
    }

    public AttributeModifierException(DataView view) {
        super("Invalid NBT Data for AttributeModifier: " + DataUtils.dataViewToNBTString(view));
    }
}
