package com.atherys.core.utils;

import com.google.common.collect.Iterables;
import org.apache.commons.lang3.time.DurationFormatUtils;
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

    public static Text formatDuration(long duration) {
        String format = "H'h' m'm' s.S's'";

        if (duration < 60000) {
            format = "s's'";
        }

        if (duration >= 60000 && duration < 3600000) {
            format = "m'm'";
        }

        if (duration >= 3600000 && duration < 86400000) {
            format = "H'h' m'm'";
        }

        if (duration >= 86400000) {
            format = "d'd' H'h'";
        }

        String formatted = DurationFormatUtils.formatDuration(duration, format, false);
        // Remove the third digit after the decimal
        formatted = new StringBuilder(formatted).deleteCharAt(formatted.length() - 2).toString();

        return Text.of(formatted);
    }
}
