package com.atherys.core.command;


import com.atherys.core.db.SpongeIdentifiable;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import javax.annotation.Nonnull;

/**
 * An extension of a CommandExecutor which provides a more organized way to transform the CommandSource from one type
 * to another. Specifically, the IngameSourceCommand will check if the source of the command is a Player, and will
 * only then proceed to transform it.
 *
 * @param <V>
 */
public interface IngameSourceCommand<V extends SpongeIdentifiable> extends SourceCommand<Player,V> {
    @Override
    @Nonnull
    default CommandResult execute(@Nonnull CommandSource src, @Nonnull CommandContext args) throws CommandException {
        if (!(src instanceof Player)) {
            throw new CommandException(Text.of("Must be in-game to execute this command."));
        }

        return execute(wrap((Player) src), args);
    }

    @Nonnull
    CommandResult execute(@Nonnull V source, @Nonnull CommandContext args) throws CommandException;
}
