package com.atherys.core.command;

import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.spec.CommandExecutor;

public interface ParameterizedCommand extends CommandExecutor {

  CommandElement[] getArguments();

}
