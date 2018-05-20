package com.atherys.core.party.commands;

import com.atherys.core.command.ParameterizedCommand;
import com.atherys.core.command.UserCommand;
import com.atherys.core.command.annotation.Aliases;
import com.atherys.core.command.annotation.Permission;
import com.atherys.core.party.Party;
import com.atherys.core.party.PartyManager;
import com.atherys.core.party.PartyMsg;
import com.atherys.core.utils.Question;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.text.Text;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Optional;

@Aliases({"add", "invite"})
@Permission("atheryscore.party.invite")
public class PartyInviteCommand extends UserCommand implements ParameterizedCommand {

    @Nonnull
    @Override
    public CommandResult execute(@Nonnull User source, @Nonnull CommandContext args) throws CommandException {
        Optional<Player> invitee = args.getOne("invitedPlayer");

        if (!invitee.isPresent()) return CommandResult.success();

        Player player = invitee.get();

        Party party = PartyManager.getInstance().getUserParty(source).orElse(Party.of(source, Arrays.asList(player)));

        PartyInviteCommand.invite(source, player, party);

        return CommandResult.success();
    }

    @Override
    public CommandElement[] getArguments() {
        return new CommandElement[]{
                GenericArguments.player(Text.of("invitedPlayer"))
        };
    }

    private static void invite(User source, Player player, Party party) {

        Question question = Question.of(Text.of(source.getName(), " has invited you to their party."))
                .addAnswer(Question.Answer.of(Text.of("Accept"), invitee -> {
                    party.addMember(invitee);
                    PartyMsg.info(player, "You have accepted ", source.getName(), "'s invite");
                    PartyMsg.info(party, player.getName(), " has joined the party!");
                }))
                .addAnswer(Question.Answer.of(Text.of("Reject"), invitee -> {
                    PartyMsg.error(player, "You have rejected ", source.getName(), "'s invite");
                }))
                .build();

        question.pollChat(player);
        PartyMsg.info(party, player.getName(), " has been invited to the party.");
    }
}
