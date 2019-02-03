package com.atherys.core.db.converter;

import com.flowpowered.math.vector.Vector3d;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.Transform;
import org.spongepowered.api.world.World;

import javax.persistence.AttributeConverter;
import java.util.Locale;

public class TransformConverter implements AttributeConverter<Transform<World>, String> {
    @Override
    public String convertToDatabaseColumn(Transform<World> attribute) {
        return String.format(Locale.US, "%s %f %f %f %f %f %f",
                attribute.getExtent().getName(),
                attribute.getPosition().getX(),
                attribute.getPosition().getY(),
                attribute.getPosition().getZ(),
                attribute.getRotation().getX(),
                attribute.getRotation().getY(),
                attribute.getRotation().getZ());
    }

    @Override
    public Transform<World> convertToEntityAttribute(String dbData) {
        String[] parsed = dbData.split(" ");
        return new Transform<>(
                Sponge.getServer().getWorld(parsed[0]).get(),
                new Vector3d(Double.parseDouble(parsed[1]), Double.parseDouble(parsed[2]), Double.parseDouble(parsed[3])),
                new Vector3d(Double.parseDouble(parsed[4]), Double.parseDouble(parsed[5]), Double.parseDouble(parsed[6]))
        );
    }
}
