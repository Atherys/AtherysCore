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
import org.spongepowered.api.text.format.TextColors;

import javax.annotation.Nonnull;
import java.util.Optional;

@Aliases("pvp")
@Permission("atheryscore.party.pvp")
public class PartyPvpCommand extends UserCommand implements ParameterizedCommand {

    @Nonnull
    @Override
    public CommandResult execute(@Nonnull User source, @Nonnull CommandContext args) throws CommandException {

        Optional<Boolean> pvp = args.getOne("toggle");

        if (!pvp.isPresent()) {
            return CommandResult.success();
        }

        if (!PartyManager.getInstance().hasUserParty(source)) {
            PartyMsg.error(source, "You are not in a party!");
            return CommandResult.success();
        }

        PartyManager.getInstance().getUserParty(source).ifPresent(party -> {
            if (!party.isLeader(source)) {
                PartyMsg.error(source, "You are not the leader of this party.");
                return;
            }

            party.setPvp(pvp.get());
            PartyMsg.info(party, "Party PvP set to ", pvp.get() ? TextColors.GREEN : TextColors.RED, pvp.get());
        });

        return CommandResult.success();
    }

    @Override
    public CommandElement[] getArguments() {
        return new CommandElement[] {
                GenericArguments.bool(Text.of("toggle"))
        };
    }
}
