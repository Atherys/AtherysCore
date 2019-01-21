package com.atherys.core.template;

import com.atherys.core.template.exception.InvalidTemplateException;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.asset.Asset;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.serializer.TextSerializers;

import java.util.List;
import java.util.Optional;

public class TemplateEngine {

    public static final String TEXT_TEMPLATE_EXTENSION = ".text";

    public static final String BOOK_TEMPLATE_EXTENSION = ".book";

    public static final String START_PAGE_TOKEN = "start page ";

    public Optional<Template> getTemplateFromAsset(Object plugin, String assetName, TemplateAttributes attributes) {
        Optional<Asset> asset = Sponge.getAssetManager().getAsset(plugin, assetName);

        try {
            if (asset.isPresent()) {
                List<String> lines = asset.get().readLines();

                if (assetName.endsWith(TEXT_TEMPLATE_EXTENSION)) {
                    return getTextTemplate(lines, attributes);
                } else if (assetName.endsWith(BOOK_TEMPLATE_EXTENSION)) {
                    return getBookTemplate(lines, attributes);
                } else {
                    throw new InvalidTemplateException("Template \"" + assetName + "\" with invalid extension requested.");
                }
            } else {
                throw new InvalidTemplateException("Could not find template with name \"" + assetName + "\"");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public Optional<Template> getBookTemplate(List<String> lines, TemplateAttributes attributes) {
        BookTemplate bookTemplate = new BookTemplate();
        bookTemplate.setAttributes(attributes);

        Text.Builder pageBuilder = Text.builder();

        for (String line : lines) {

            if ( line.isEmpty() ) {
                bookTemplate.addPage(pageBuilder.build());
                pageBuilder = Text.builder();
                continue;
            }

            pageBuilder.append(TextSerializers.FORMATTING_CODE.deserialize(line), Text.NEW_LINE);
        }

        return Optional.of(bookTemplate);
    }

    public Optional<Template> getTextTemplate(List<String> lines, TemplateAttributes attributes) {
        TextTemplate template = new TextTemplate();
        template.setAttributes(attributes);

        Text.Builder textBuilder = Text.builder();

        for (String line : lines) {
            textBuilder.append(TextSerializers.FORMATTING_CODE.deserialize(line), Text.NEW_LINE);
        }

        return Optional.of(template);
    }

}
