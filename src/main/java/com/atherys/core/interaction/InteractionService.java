package com.atherys.core.interaction;

import org.spongepowered.api.entity.living.player.Player;

public interface InteractionService {

    void startInteraction(Player player);

    void endInteraction(Player player);

    boolean isInteracting(Player player);

    void interact(Player player);
}
