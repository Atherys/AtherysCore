package com.atherys.core.commands;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.User;

import javax.annotation.Nonnull;

public abstract class UserCommand implements CommandExecutor {

    @Override
    @Nonnull
    public CommandResult execute( @Nonnull CommandSource src, @Nonnull CommandContext args ) throws CommandException {
        if ( !( src instanceof User ) ) return CommandResult.empty();
        User user = ( User ) src;
        return execute( user, args );
    }

    @Nonnull
    public abstract CommandResult execute( @Nonnull User source, @Nonnull CommandContext args ) throws CommandException;

}
