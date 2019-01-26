package com.atherys.core.db.converter;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import javax.persistence.AttributeConverter;

public class LocationConverter implements AttributeConverter<Location<World>,String> {
    @Override
    public String convertToDatabaseColumn(Location<World> attribute) {
        return String.format("%f %f %f %s", attribute.getX(), attribute.getY(), attribute.getZ(), attribute.getExtent().getName());
    }

    @Override
    public Location<World> convertToEntityAttribute(String dbData) {
        String[] parsed = dbData.split(" ");
        return Sponge.getGame().getServer().getWorld(parsed[3]).get().getLocation(
                Double.parseDouble(parsed[0]),
                Double.parseDouble(parsed[1]),
                Double.parseDouble(parsed[2])
        );
    }
}
