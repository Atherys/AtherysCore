package com.atherys.core.template;

import org.apache.commons.lang3.ClassUtils;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.TextRepresentable;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class TemplateAttributes {

    public static class Builder {
        TemplateAttributes attributes = new TemplateAttributes();

        public Builder add(String name, Object value) {
            attributes.addAttribute(name, value);
            return this;
        }

        public TemplateAttributes build() {
            return attributes;
        }
    }

    private Map<String, TextRepresentable> attributes = new HashMap<>();

    public TemplateAttributes() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public void addAttribute(String name, Object value) {

        if (ClassUtils.isPrimitiveOrWrapper(value.getClass()) || value instanceof String || value instanceof TextRepresentable) {
            attributes.put(name, Text.of(value));
            return;
        }

        for (Field field : value.getClass().getFields()) {

            String attribName = name + "." + field.getName();

            try {
                boolean accessible = field.isAccessible();

                field.setAccessible(true);
                addAttribute(attribName, field.get(value));
                field.setAccessible(accessible);
            } catch (IllegalAccessException e) {
                addAttribute(attribName, Template.DEFAULT_ATTRIBUTE_VALUE);
            }
        }
    }

    public void removeAttribute(String name) {
        attributes.remove(name);
    }

    public Text getAttribute(String name) {
        return attributes.getOrDefault(name, Text.of(name)).toText();
    }

    public Map<String, TextRepresentable> getAttributes() {
        return attributes;
    }
}
