package com.atherys.core.party.commands;

import com.atherys.core.command.UserCommand;
import com.atherys.core.command.annotation.Aliases;
import com.atherys.core.command.annotation.Permission;
import com.atherys.core.party.PartyManager;
import com.atherys.core.party.PartyMsg;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.entity.living.player.User;

import javax.annotation.Nonnull;

@Aliases({"disband", "remove"})
@Permission("atheryscore.party.disband")
public class PartyDisbandCommand extends UserCommand {

    @Nonnull
    @Override
    public CommandResult execute(@Nonnull User source, @Nonnull CommandContext args) throws CommandException {
        if (!PartyManager.getInstance().hasUserParty(source)) {
            PartyMsg.error(source, "You are not in a party!");
            return CommandResult.success();
        }

        PartyManager.getInstance().getUserParty(source).ifPresent(party -> {
            if (!party.isLeader(source)) {
                PartyMsg.error(source, "You are not the leader of this party.");
                return;
            }

            PartyMsg.error(party, "Your party has been disbanded.");
            PartyManager.getInstance().removeParty(party);
        });

        return CommandResult.success();
    }

}
