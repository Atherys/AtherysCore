package com.atherys.core.template;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

public interface Template<T> {

    Text DEFAULT_ATTRIBUTE_VALUE = Text.of("null");

    String ARGUMENT_BEGIN = "${";

    String ARGUMENT_END = "}";

    void setAttributes(TemplateAttributes attributes);

    void addAttribute(String key, Object attribute);

    void removeAttribute(String key);

    Text getAttribute(String key);

    T render();

    void showTo(Player player);

}
