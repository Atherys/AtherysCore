package com.atherys.core.serialize;

import com.google.common.reflect.TypeToken;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import ninja.leaping.configurate.objectmapping.serialize.TypeSerializer;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

public class LocationTypeSerializer implements TypeSerializer<Location<World>> {
    @Override
    public @Nullable Location<World> deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) throws ObjectMappingException {
        String worldName = value.getNode("world").getString();
        World world = Sponge.getServer().getWorld(worldName).get();
        double x = value.getNode("x").getDouble();
        double y = value.getNode("y").getDouble();
        double z = value.getNode("z").getDouble();

        return world.getLocation(x, y, z);
    }

    @Override
    public void serialize(@NonNull TypeToken<?> type, @Nullable Location<World> obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
        value.getNode("world").setValue(obj.getExtent().getName());
        value.getNode("x").setValue(obj.getPosition().getX());
        value.getNode("y").setValue(obj.getPosition().getY());
        value.getNode("z").setValue(obj.getPosition().getZ());
    }
}
