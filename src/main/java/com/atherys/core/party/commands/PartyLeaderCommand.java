package com.atherys.core.party.commands;

import com.atherys.core.commands.UserCommand;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.entity.living.player.User;

import javax.annotation.Nonnull;

public class PartyLeaderCommand extends UserCommand {

    @Nonnull
    @Override
    public CommandResult execute ( @Nonnull User source, @Nonnull CommandContext args ) throws CommandException {
        return null;
    }

}
