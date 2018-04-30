package com.atherys.core.party.commands;

import com.atherys.core.commands.UserCommand;
import com.atherys.core.party.Party;
import com.atherys.core.party.PartyManager;
import com.atherys.core.party.PartyMsg;
import com.atherys.core.utils.Question;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;

import javax.annotation.Nonnull;
import java.util.Optional;

public class PartyInviteCommand extends UserCommand {

    @Nonnull
    @Override
    public CommandResult execute( @Nonnull User user, @Nonnull CommandContext args ) throws CommandException {
        Optional<String> inviteeArg = args.getOne( "invitedPlayer" );
        if ( !inviteeArg.isPresent() ) return CommandResult.empty();

		Optional<Player> invitee = Sponge.getServer().getPlayer(inviteeArg.get());

        if ( !invitee.isPresent() ){
            PartyMsg.error(user, "That player is not online.");
            return CommandResult.empty();
        }
        // if invitee is already in another party, don't invite
        if ( PartyManager.getInstance().hasPlayerParty( invitee.get() ) ) {
            PartyMsg.error( user, "That player is already in another party." );
            return CommandResult.success();
        }

        Optional<Party> playerParty = PartyManager.getInstance().getPlayerParty( user );

        if ( playerParty.isPresent() && playerParty.get().isLeader( user ) ) {
            // If player has party and is leader of that party, invitee will be invited to player's party
            Party party = playerParty.get();

            Question q = Question.of( Text.of( user.getName(), " has invited you to their party. Would you like to join?" ) )
                    .addAnswer( Question.Answer.of( Text.of( TextStyles.BOLD, TextColors.DARK_GREEN, "Yes" ), ( respondent ) -> {
                        party.addPlayer( respondent );
                        PartyMsg.info( party, respondent.getName(), " has joined your party!" );
                    } ) )
                    .addAnswer( Question.Answer.of( Text.of( TextStyles.BOLD, TextColors.DARK_RED, "No" ), ( respondent ) -> PartyMsg.error( user, respondent.getName(), " has refused to join your party." ) ) )
                    .build();

            q.pollChat( invitee.get() );

        } else {
            // If player does not have a party, and neither does invitee, then the party will be created when invitee accepts party invite

            Question q = Question.of( Text.of( user.getName(), " has invited you to their party. Would you like to join?" ) )
                    .addAnswer( Question.Answer.of( Text.of( TextStyles.BOLD, TextColors.DARK_GREEN, "Yes" ), ( respondent ) -> {

                        if ( !user.isOnline() ) {
                            // If when party invite is accepted, player is offline, party will not be created.
                            PartyMsg.error( respondent, user.getName(), " has gone offline since sending you that invite. Party could not be created. Sorry!" );
                            return;
                        }

                        Party party = Party.of( user, invitee.get() );
                        PartyMsg.info( party, "Your party has been created! Do ", TextColors.GREEN, TextStyles.BOLD, "/party", TextColors.DARK_AQUA, TextStyles.RESET, " for a list of members." );
                    } ) )
                    .addAnswer( Question.Answer.of( Text.of( TextStyles.BOLD, TextColors.DARK_RED, "No" ), ( respondent ) -> PartyMsg.error( user, respondent.getName(), " has refused to join your party." ) ) )
                    .build();

            q.pollChat( invitee.get() );
        }

        return CommandResult.success();
    }

    public CommandSpec getCommandSpec() {
        return CommandSpec.builder()
                .permission( "atherys.core.party.invite" )
                .executor( this )
                .arguments(
                        GenericArguments.string( Text.of( "invitedPlayer" ) )
                )
                .build();
    }
}
