package com.atherys.core.template;

import org.spongepowered.api.text.Text;

public interface Template<T> {

    Text DEFAULT_ATTRIBUTE_VALUE = Text.of("null");

    String ARGUMENT_BEGIN = "${";

    String ARGUMENT_END = "}";

    void addAttribute(String key, Text attribute);

    void removeAttribute(String key);

    Text getAttribute(String key);

    default void addAttribute(String key, Object... attributes) {
        addAttribute(key, Text.of(attributes));
    }

    T render();

}
