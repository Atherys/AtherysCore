package com.atherys.core.party.commands;

import com.atherys.core.commands.UserCommand;
import com.atherys.core.party.PartyManager;
import com.atherys.core.party.PartyMsg;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class PartyCommand extends UserCommand {

    @Override
    public CommandResult execute( User user, CommandContext args ) throws CommandException {

        PartyManager.getInstance().getPlayerParty( user ).ifPresent( party -> {
            Text.Builder partyMembers = Text.builder();

            party.getMembers().forEach( partyMember -> {
                if ( party.isLeader( partyMember ) ) {// is leader?
                    partyMembers.append( Text.of( TextColors.RED, partyMember.getName(), "; " ) );
                } else {
                    partyMembers.append( Text.of( TextColors.DARK_AQUA, partyMember.getName(), "; " ) );
                }
            } );

            PartyMsg.info( user, "Party Members: ", partyMembers.build() );
        } );

        return CommandResult.success();
    }


    public CommandSpec getCommandSpec() {
        return CommandSpec.builder()
                .permission( "atherys.core.party" )
                .executor( this )
                .child( new PartyInviteCommand().getCommandSpec(), "invite" )
                .child( new PartyLeaveCommand().getCommandSpec(), "leave" )
                .child( new PartyKickCommand().getCommandSpec(), "kick" )
                //.child( new PartyLeaderCommand().getCommandSpec(), "leader" )
                .build();
    }

}
