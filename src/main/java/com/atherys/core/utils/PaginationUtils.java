package com.atherys.core.utils;

import com.atherys.core.command.annotation.Aliases;
import com.atherys.core.command.annotation.Description;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.service.pagination.PaginationList;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import java.util.ArrayList;
import java.util.List;

public class PaginationUtils {

    /**
     * Creates a pagination list from an array of commands and descriptions. 
     */
    public static PaginationList paginate(String title, List<Class<? extends CommandExecutor>> children) {

        List<Text> content = new ArrayList<>(children.size());
        for (Class<? extends CommandExecutor> command : children){
            if (command.isAnnotationPresent(Description.class) && command.isAnnotationPresent(Aliases.class)){
                String desc = command.getAnnotation(Description.class).value();
                String alias = command.getAnnotation(Aliases.class).value()[0];
                content.add(formatHelp(alias, desc));
            }
        }

        return PaginationList.builder()
                .title(Text.of(TextColors.GOLD, title))
                .padding(Text.of(TextColors.DARK_GREEN, "="))
                .contents(content)
                .build();
    }

    /**
     * Formats a command and a description.
     */
    private static Text formatHelp(String command, String description) {
        return Text.of(TextColors.GOLD, command, "-", TextColors.DARK_GREEN, description);
    }
}
