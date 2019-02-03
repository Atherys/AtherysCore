package com.atherys.core.command.exception;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class CommandNotImplementedException extends CommandException {
    public CommandNotImplementedException() {
        super(Text.of(TextColors.RED, "This command has not been implemented yet."));
    }
}
