package com.atherys.core.script.lib.player;

import org.spongepowered.api.entity.living.player.Player;

import java.util.UUID;
import java.util.function.Function;

public class GetPlayerFromUUID implements Function<UUID, Player> {
    @Override
    public Player apply(UUID uuid) {
        return null;
    }
}
