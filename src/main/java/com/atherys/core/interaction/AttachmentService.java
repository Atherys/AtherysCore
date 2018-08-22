package com.atherys.core.interaction;

import org.spongepowered.api.entity.living.player.Player;

import java.util.Optional;

public interface AttachmentService<T> {
    void startAttachment(Player player, T object);

    void endAttachment(Player player);

    Optional<T> getAttachment(Player player);

    void applyAttachment(Player player);

    boolean isAttaching(Player player);

    void startRemoval(Player player);

    void endRemoval(Player player);

    boolean isRemoving(Player player);
}
