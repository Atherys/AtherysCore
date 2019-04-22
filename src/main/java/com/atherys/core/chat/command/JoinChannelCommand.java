package com.atherys.core.chat.command;

import com.atherys.core.AtherysCore;
import com.atherys.core.command.ParameterizedCommand;
import com.atherys.core.command.annotation.Aliases;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.text.Text;

@Aliases("join")
public class JoinChannelCommand implements ParameterizedCommand {

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) {
        AtherysCore.getChatFacade().joinChatChannel(src, args.<String>getOne("channel").orElse(""));
        return CommandResult.success();
    }

    @Override
    public CommandElement[] getArguments() {
        return new CommandElement[]{
                GenericArguments.string(Text.of("channel"))
        };
    }
}
