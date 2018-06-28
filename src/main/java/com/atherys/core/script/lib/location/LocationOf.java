package com.atherys.core.script.lib.location;

import com.atherys.core.utils.QuadFunction;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

public class LocationOf implements QuadFunction<World, Double, Double, Double, Location<World>> {
    @Override
    public Location<World> apply(World world, Double x, Double y, Double z) {
        return new Location<>(world, x, y, z);
    }
}
