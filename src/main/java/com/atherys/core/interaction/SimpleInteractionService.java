package com.atherys.core.interaction;

import org.spongepowered.api.entity.living.player.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Like {@link InteractionService} but for interactions that don't involve another object.
 */
public abstract class SimpleInteractionService {
    private Set<UUID> interactors;

    public SimpleInteractionService() {
        interactors = new HashSet<>();
    }

    public void startInteraction(Player player) {
        interactors.add(player.getUniqueId());
    }

    public void endInteraction(Player player) {
        interactors.remove(player.getUniqueId());
    }

    public boolean isInteracting(Player player) {
        return interactors.contains(player.getUniqueId());
    }

    public abstract void interact(Player player);
}
