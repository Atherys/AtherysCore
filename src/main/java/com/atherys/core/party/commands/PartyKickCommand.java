package com.atherys.core.party.commands;

import com.atherys.core.command.ParameterizedCommand;
import com.atherys.core.command.UserCommand;
import com.atherys.core.command.annotation.Aliases;
import com.atherys.core.command.annotation.Permission;
import com.atherys.core.party.PartyManager;
import com.atherys.core.party.PartyMsg;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.text.Text;

import javax.annotation.Nonnull;
import java.util.Optional;

@Aliases("kick")
@Permission("atheryscore.party.kick")
public class PartyKickCommand extends UserCommand implements ParameterizedCommand {

    @Nonnull
    @Override
    public CommandResult execute(@Nonnull User source, @Nonnull CommandContext args) throws CommandException {
        Optional<User> kickedUser = args.getOne("kickedPlayer");

        if (!kickedUser.isPresent()) {
            return CommandResult.success();
        }

        if (!PartyManager.getInstance().areUsersInSameParty(source, kickedUser.get())) {
            PartyMsg.error(source, "That player is not in your party!");
            return CommandResult.success();
        }

        if (kickedUser.get().equals(source)) {
            PartyMsg.error(source, "You can't kick yourself!");
            return CommandResult.success();
        }

        PartyManager.getInstance().getUserParty(source).ifPresent(party -> {
            if (!party.isLeader(source)) {
                PartyMsg.error(source, "You are not the leader of this party.");
                return;
            }

            party.removeMember(kickedUser.get());
            PartyMsg.error(party, kickedUser.get().getName(), " has been kicked from the party by ", source.getName());
            PartyMsg.error(kickedUser.get(), "You have been kicked from the party");
        });

        return CommandResult.success();
    }

    @Override
    public CommandElement[] getArguments() {
        return new CommandElement[]{
                GenericArguments.user(Text.of("kickedPlayer"))
        };
    }
}
