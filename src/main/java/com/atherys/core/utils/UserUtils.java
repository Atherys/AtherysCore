package com.atherys.core.utils;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.service.user.UserStorageService;

import java.util.Optional;
import java.util.UUID;

public final class UserUtils {

    private static UserStorageService userStorage;

    static {
        userStorage = Sponge.getServiceManager().provide(UserStorageService.class).get();
    }

    /**
     * @param uuid the UUID of the player
     * @return An offline User object, or an online Player object. If neither is available, returns
     * empty Optional.
     */
    public static Optional<? extends User> getUser(UUID uuid) {
        Optional<Player> onlinePlayer = Sponge.getServer().getPlayer(uuid);

        return onlinePlayer.isPresent() ? onlinePlayer : userStorage.get(uuid);
    }

    /**
     * @param name the name of the player
     */
    public static Optional<? extends User> getUser(String name) {
        Optional<? extends User> onlinePlayer = Sponge.getServer().getPlayer(name);

        return onlinePlayer.isPresent() ? onlinePlayer : userStorage.get(name);
    }

}
