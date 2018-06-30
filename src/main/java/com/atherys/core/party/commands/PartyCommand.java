package com.atherys.core.party.commands;

import com.atherys.core.command.UserCommand;
import com.atherys.core.command.annotation.Aliases;
import com.atherys.core.command.annotation.Children;
import com.atherys.core.command.annotation.Permission;
import com.atherys.core.party.Party;
import com.atherys.core.party.PartyManager;
import com.atherys.core.party.PartyMsg;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import java.util.Optional;

@Aliases("party")
@Children({
        PartyInviteCommand.class,
        PartyKickCommand.class,
        PartyLeaderCommand.class,
        PartyLeaveCommand.class,
        PartyDisbandCommand.class,
        PartyPvpCommand.class
})
@Permission("atheryscore.party")
public class PartyCommand extends UserCommand {

    @Override
    public CommandResult execute(User user, CommandContext args) throws CommandException {

        Optional<Party> userParty = PartyManager.getInstance().getUserParty(user);

        if (userParty.isPresent()) {
            Text.Builder partyMembers = Text.builder();

            userParty.get().getMembers().forEach(partyMember -> {
                if (userParty.get().isLeader(partyMember)) {// is leader?
                    partyMembers.append(Text.of(TextColors.RED, partyMember.getName(), "; "));
                } else {
                    partyMembers.append(Text.of(TextColors.DARK_AQUA, partyMember.getName(), "; "));
                }
            });

            PartyMsg.info(user, "Party Members: ", partyMembers.build());
        } else {
            PartyMsg.error(user, "You are not currently in a party with anyone else.");
        }

        return CommandResult.success();
    }

}
