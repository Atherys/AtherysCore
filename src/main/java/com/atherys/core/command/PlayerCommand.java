package com.atherys.core.command;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import javax.annotation.Nonnull;

public interface PlayerCommand extends CommandExecutor {
    @Override
    @Nonnull
    default CommandResult execute(@Nonnull CommandSource src, @Nonnull CommandContext args) throws CommandException {
        if (!(src instanceof Player)) {
            throw new CommandException(Text.of("Must be in-game to execute this command."));
        }

        return execute((Player) src, args);
    }

    @Nonnull
    CommandResult execute(@Nonnull Player source, @Nonnull CommandContext args) throws CommandException;
}
