package com.atherys.core.utils;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.cause.entity.damage.source.EntityDamageSource;
import org.spongepowered.api.event.cause.entity.damage.source.IndirectEntityDamageSource;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.extent.EntityUniverse;

import java.util.Optional;
import java.util.UUID;

public class EntityUtils {

    public static Optional<Entity> getEntity(UUID id) {
        for (World world : Sponge.getServer().getWorlds()) {
            Optional<Entity> entity = world.getEntity(id);
            if (entity.isPresent()) {
                return entity;
            }
        }
        return Optional.empty();
    }

    public static Optional<Entity> getNonPlayerFacingEntity(Entity source, double distance) {
        return source.getWorld().getIntersectingEntities(source, distance).stream()
                .map(EntityUniverse.EntityHit::getEntity)
                .filter(entity -> entity != source)
                .findFirst();
    }

    public static Optional<Player> playerAttackedEntity(EntityDamageSource source) {
        Entity root = getRootEntity(source);

        return root instanceof Player ? Optional.of((Player) root) : Optional.empty();
    }

    public static Entity getRootEntity(EntityDamageSource source) {
        if (source instanceof IndirectEntityDamageSource) {
            IndirectEntityDamageSource indirect = (IndirectEntityDamageSource) source;
            return indirect.getIndirectSource();
        }

        return source.getSource();
    }
}
