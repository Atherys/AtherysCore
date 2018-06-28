package com.atherys.core.script.lib.entity;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.EntitySnapshot;
import org.spongepowered.api.entity.EntityType;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.util.function.BiFunction;
import java.util.function.Function;

public class CreateEntity implements BiFunction<String, Location<World>, Entity> {

    @Override
    public Entity apply(String entityType, Location<World> worldLocation) {
        return Sponge.getRegistry().getType(EntityType.class, entityType).map( type -> worldLocation.getExtent().createEntity(type, worldLocation.getPosition())).orElse(null);
    }

}
