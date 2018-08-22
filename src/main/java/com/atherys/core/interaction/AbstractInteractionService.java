package com.atherys.core.interaction;

import org.spongepowered.api.entity.living.player.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Like {@link AttachmentService} but for interactions that don't involve another object.
 */
public abstract class AbstractInteractionService implements InteractionService{
    private Set<UUID> interactors;

    public AbstractInteractionService() {
        interactors = new HashSet<>();
    }

    @Override
    public void startInteraction(Player player) {
        interactors.add(player.getUniqueId());
    }

    @Override
    public void endInteraction(Player player) {
        interactors.remove(player.getUniqueId());
    }

    @Override
    public boolean isInteracting(Player player) {
        return interactors.contains(player.getUniqueId());
    }
}
