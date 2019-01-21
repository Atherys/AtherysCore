package com.atherys.core.template;

import com.atherys.core.utils.TextUtils;
import org.spongepowered.api.text.Text;

public abstract class AbstractTemplate<T> implements Template<T> {

    private TemplateAttributes templateAttributes = new TemplateAttributes();

    @Override
    public void setAttributes(TemplateAttributes attributes) {
        templateAttributes = attributes;
    }

    @Override
    public void addAttribute(String key, Object attribute) {
        templateAttributes.addAttribute(key, attribute);
    }

    @Override
    public void removeAttribute(String key) {
        templateAttributes.removeAttribute(key);
    }

    @Override
    public Text getAttribute(String key) {
        return templateAttributes.getAttribute(key);
    }

    protected Text applyAttributesTo(Text text) {
        Text[] result = new Text[] {text};
        templateAttributes.getAttributes().forEach((k,v) -> result[0] = TextUtils.replace(result[0], k, v.toText()));
        return result[0];
    }

}
