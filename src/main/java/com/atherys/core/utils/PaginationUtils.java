package com.atherys.core.utils;

import com.atherys.core.AtherysCore;
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
    public static PaginationList paginate(String title, List<Class<? extends CommandExecutor>> children, List<String> commands) {

        if (commands.size() != children.size()){
            AtherysCore.getInstance().getLogger().error("Descriptions and children are of different lengths; not all commands will be formatted.");
        }

        List<Text> content = new ArrayList<>(commands.size());
        int i = 0;
        for (String command : commands){
            Class clazz = children.get(i);
            if (clazz.isAnnotationPresent(Description.class)){
                String desc = ((Description) clazz.getAnnotation(Description.class)).value();
                content.add(PaginationUtils.formatHelp(command, desc));
            }
            i++;
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
    public static Text formatHelp(String command, String description) {
        return Text.of(TextColors.GOLD, command, "-", TextColors.DARK_GREEN, description);
    }
}
