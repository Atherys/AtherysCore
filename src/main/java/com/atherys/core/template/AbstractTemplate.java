package com.atherys.core.template;

import com.atherys.core.utils.TextUtils;
import org.spongepowered.api.text.Text;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractTemplate<T> implements Template<T> {

    private Map<String, Text> templateAttributes = new HashMap<>();

    @Override
    public void addAttribute(String key, Text attribute) {
        templateAttributes.put(key, attribute);
    }

    @Override
    public void removeAttribute(String key) {
        templateAttributes.remove(key);
    }

    @Override
    public Text getAttribute(String key) {
        return templateAttributes.getOrDefault(key, DEFAULT_ATTRIBUTE_VALUE);
    }

    protected Text applyAttributesTo(Text text) {
        Text result = text;

        for (Map.Entry<String, Text> attribute : templateAttributes.entrySet()) {
            result = TextUtils.replace(result, ARGUMENT_BEGIN + attribute.getKey() + ARGUMENT_END, attribute.getValue());
        }

        return result;
    }

}
