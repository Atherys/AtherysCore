package com.atherys.core.party.commands;

import com.atherys.core.commands.UserCommand;
import com.atherys.core.party.PartyManager;
import com.atherys.core.party.PartyMsg;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.entity.living.player.User;

public class PartyLeaveCommand extends UserCommand{
    @Override
    public CommandResult execute ( User user, CommandContext args ) throws CommandException {

        if ( !PartyManager.getInstance().hasPlayerParty( user ) ) {
            PartyMsg.info( user, "You are not in a party.");
            return CommandResult.success();
        }

        PartyManager.getInstance().getPlayerParty( user ).ifPresent( party -> {
            party.removePlayer( user );
            PartyMsg.error( party, user.getName(), " has left the party.");
            PartyMsg.error( user, "You have left the party.");
        });

        return CommandResult.success();
    }

    public CommandSpec getCommandSpec() {
        return CommandSpec.builder()
                .permission( "atherys.core.party.leave" )
                .executor(this)
                .build();
    }

}
