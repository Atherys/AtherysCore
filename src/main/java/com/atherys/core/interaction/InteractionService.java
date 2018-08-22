package com.atherys.core.interaction;

import org.spongepowered.api.entity.living.player.Player;

import java.util.*;

/**
 * This class represents interacts between Players and an object. Usually, this is the go-between for commands
 * that require some action after use.
 * @param <T> the object the player is attaching
 */
public abstract class InteractionService<T> {
    private Map<UUID, T> attachers;
    private Set<UUID> removers;

    public void startAttachment(Player player, T object) {
        attachers.put(player.getUniqueId(), object);
    }

    public void endAttachment(Player player) {
        attachers.remove(player.getUniqueId());
    }

    private Optional<T> getAttachment(Player player) {
        return Optional.ofNullable(attachers.get(player.getUniqueId()));
    }

    public abstract void applyAttachment(Player player);

    public boolean isAttaching(Player player) {
        return attachers.containsKey(player.getUniqueId());
    }

    public void startRemoval(Player player) {
        removers.add(player.getUniqueId());
    }

    public void endRemoval(Player player) {
        removers.remove(player.getUniqueId());
    }

    public boolean isRemoving(Player player) {
        return removers.contains(player.getUniqueId());
    }

    public InteractionService() {
        this.attachers = new HashMap<>();
        this.removers = new HashSet<>();
    }
}
