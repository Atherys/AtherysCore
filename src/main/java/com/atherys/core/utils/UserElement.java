package com.atherys.core.utils;

import com.google.common.collect.ImmutableMap;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.*;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.service.user.UserStorageService;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.TextTemplate;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * A {@link User} {@link CommandElement} that only completes for online players, to avoid fetching all players.
 */
public class UserElement extends CommandElement {
    private static TextTemplate exception = TextTemplate.of("Input value ", TextTemplate.arg("player"), " was not a player.");

    public UserElement(Text key) {
        super(key);
    }

    @Override
    @Nullable
    protected Object parseValue(CommandSource source, CommandArgs args) throws ArgumentParseException {
        String player = args.next();

        if (player.isEmpty() || player.length() > 16) {
            throw exception(player, args);
        }

        Optional<? extends User> ret = Sponge.getGame().getServer().getPlayer(player);
        if (!ret.isPresent()) {
            ret = Sponge.getServiceManager().provideUnchecked(UserStorageService.class).get(player);
        }
        return ret.orElseThrow(() -> exception(player, args));
    }

    @Override
    public List<String> complete(CommandSource src, CommandArgs args, CommandContext context) {
        return Sponge.getGame().getServer().getOnlinePlayers().stream()
                .map(Player::getName)
                .filter(name -> args.nextIfPresent().map(name::startsWith).orElse(true))
                .collect(Collectors.toList());
    }

    private static ArgumentParseException exception(String player, CommandArgs args) {
        return args.createError(exception.apply(ImmutableMap.of("player", player)).build());
    }
}
