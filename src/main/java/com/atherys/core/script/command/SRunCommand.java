package com.atherys.core.script.command;

import com.atherys.core.AtherysCore;
import com.atherys.core.command.ParameterizedCommand;
import com.atherys.core.command.annotation.Aliases;
import com.atherys.core.command.annotation.Description;
import com.atherys.core.command.annotation.Permission;
import com.atherys.core.script.AtherysScript;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Aliases("srun")
@Description("Runs a script")
@Permission("atherysquests.admin.srun")
public class SRunCommand implements ParameterizedCommand {

    private static final Text SUCCESS_PREFIX = Text.of(TextColors.DARK_GREEN, "[", TextColors.GREEN, "SUCCESS", TextColors.DARK_GREEN, "] ", TextColors.RESET);
    private static final Text ERROR_PREFIX = Text.of(TextColors.DARK_RED, "[", TextColors.RED, "ERROR", TextColors.DARK_RED, "] ", TextColors.RED);

    @Override
    public CommandElement[] getArguments() {
        return new CommandElement[]{
                GenericArguments.remainingRawJoinedStrings(Text.of("javascript"))
        };
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        String script = args.<String>getOne("javascript").orElse("");

        if (script.isEmpty()) return CommandResult.empty();

        else {
            try {

                long before = System.currentTimeMillis();

                Object result = AtherysCore.getScriptingEngine().run(script);

                long after = System.currentTimeMillis();

                src.sendMessage(Text.of(SUCCESS_PREFIX, TextColors.GREEN, "Executed: ", TextColors.RESET, script));
                src.sendMessage(Text.of(SUCCESS_PREFIX, TextColors.GREEN, "Result: ", TextColors.RESET, result == null ? "None" : result));
                src.sendMessage(Text.of(SUCCESS_PREFIX, TextColors.GREEN, "Time: ", TextColors.RESET, formatDuration(after - before)));

            } catch (Exception e) {

                src.sendMessage(Text.of(ERROR_PREFIX, TextColors.RED, "Executed: ", TextColors.RESET, script));
                src.sendMessage(Text.of(ERROR_PREFIX, TextColors.RED, "Error: ", TextColors.RESET, e.getMessage()));

                if (e.getMessage() == null) e.printStackTrace();

            }
        }

        return CommandResult.success();
    }

    private static String formatDuration(long millis) {
        return String.format("%d.%d", TimeUnit.MILLISECONDS.toSeconds(millis), millis % 1000);
    }
}
