package com.atherys.core.command;

import com.atherys.core.db.SpongeIdentifiable;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;

import javax.annotation.Nonnull;

/**
 * An extension of a CommandExecutor which
 *
 * @param <T>
 * @param <V>
 */
public interface SourceCommand<T extends CommandSource, V extends SpongeIdentifiable> extends CommandExecutor {
    @Override
    @Nonnull
    default CommandResult execute(@Nonnull CommandSource src, @Nonnull CommandContext args) throws CommandException {
        return execute(wrap((T) src), args);
    }

    @Nonnull
    CommandResult execute(@Nonnull V source, @Nonnull CommandContext args) throws CommandException;

    V wrap(T user) throws CommandException;
}
