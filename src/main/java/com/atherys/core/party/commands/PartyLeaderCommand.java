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

@Aliases("leader")
@Permission("atheryscore.party.leader")
public class PartyLeaderCommand extends UserCommand implements ParameterizedCommand {

    @Nonnull
    @Override
    public CommandResult execute(@Nonnull User source, @Nonnull CommandContext args) throws CommandException {
        Optional<User> newLeader = args.getOne("newLeader");

        if (!newLeader.isPresent()) {
            return CommandResult.success();
        }

        if (!PartyManager.getInstance().hasUserParty(source)) {
            PartyMsg.error(source, "You are not in a party!");
            return CommandResult.success();
        }

        if (newLeader.get().equals(source)) {
            PartyMsg.error(source, "You are already the leader of this party.");
            return CommandResult.success();
        }

        if (!PartyManager.getInstance().areUsersInSameParty(newLeader.get(), source)) {
            PartyMsg.error(source, "That player is not in your party!");
            return CommandResult.success();
        }

        PartyManager.getInstance().getUserParty(source).ifPresent(party -> {
            if (!party.isLeader(source)) {
                PartyMsg.error(source, "You are not the leader of this party.");
                return;
            }

            party.setLeader(newLeader.get());
            PartyMsg.info(party, newLeader.get().getName(), " is now the leader of the party.");
        });

        return CommandResult.success();
    }

    @Override
    public CommandElement[] getArguments() {
        return new CommandElement[]{
                GenericArguments.user(Text.of("newLeader"))
        };
    }
}
