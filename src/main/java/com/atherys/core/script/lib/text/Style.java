package com.atherys.core.script.lib.text;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.text.format.TextStyle;
import org.spongepowered.api.text.format.TextStyles;

import java.util.function.Function;

public class Style implements Function<String, TextStyle> {
    @Override
    public TextStyle apply(String textStyleId) {
        return TextStyles.NONE; //Sponge.getRegistry().getType(TextStyle.class, textStyleId).orElse(TextStyles.NONE);
    }
}
