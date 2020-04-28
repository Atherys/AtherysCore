package com.atherys.core.utils;

import com.google.common.collect.Iterables;
import org.spongepowered.api.text.Text;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class TextUtils {

    public static Text replace(Text text, String argument, Text replacement) {
        Text t;

        if ( text.getChildren().isEmpty() ) {
            t = text;
        } else {
            t = text.toBuilder()
                    .removeAll()
                    .append(text.getChildren().stream().map(x -> replace(x, argument, replacement)).collect(Collectors.toList()))
                    .build();
        }

        String plain = t.toPlainSingle();

        if (!plain.contains(argument)) {
            return t;
        }

        if (plain.equals(argument)) {
            return replacement;
        }

        Text.Builder builder = Text.builder();

        List<String> strs = Arrays.asList(plain.split(Pattern.quote(argument)));

        for (String str : Iterables.limit(strs, strs.size() - 1)) {
            builder.append(Text.of(str));
            builder.append(replacement);
        }

        builder.append(Text.of(strs.get(strs.size() - 1)));

        if (plain.endsWith(argument)) {
            builder.append(replacement);
        }

        builder.style(text.getStyle()).color(text.getColor()).append(text.getChildren());
        return builder.build();
    }

}
