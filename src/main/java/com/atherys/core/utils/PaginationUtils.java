package com.atherys.core.utils;

import com.atherys.core.command.annotation.Description;
import com.atherys.core.AtherysCore;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.service.pagination.PaginationList;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class PaginationUtils {

    //Where the value appears in the Annotation's toString
    private static final int valueStart = 55;
	 	
    /**
     * Creates a pagination list from an array of commands and descriptions. 
     */
    public static PaginationList paginate(String title, Class<? extends CommandExecutor>[] children, String...commands) {

        if (commands.length != children.length){
            AtherysCore.getInstance().getLogger().error("Descriptions and children are of different lengths; not all commands will be formatted.");
        }

        Text[] content = new Text[commands.length];
        int i = 0;
        for (String command : commands){
            Class clazz = children[i];
            if (clazz.isAnnotationPresent(Description.class)){
                String desc = clazz.getAnnotation(Description.class).toString();
                content[i] = PaginationUtils.formatHelp(command, desc.substring(valueStart, desc.length() - 1));
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
