package com.atherys.core.world;

import com.flowpowered.math.vector.Vector3d;
import com.flowpowered.math.vector.Vector3i;
import org.apache.commons.lang3.ObjectUtils;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.UUID;

/**
 * An immutable representation of a location, which does not contain an Extent object, but the uuid of a world.
 * AtherysLocation can only be used to represent a {@link Location} with a {@link World} extent.
 */
public class AtherysLocation {

    private UUID worldUuid = null;

    private Vector3d position = null;

    private Vector3i blockPosition = null;

    private Vector3i chunkPosition = null;

    private Vector3i biomePosition = null;

    public static AtherysLocation of(Location<World> location) {
        return new AtherysLocation(
                location.getExtent().getUniqueId(),
                location.getPosition(),
                location.getBlockPosition(),
                location.getChunkPosition(),
                location.getBiomePosition()
        );
    }

    public AtherysLocation() {
    }

    private AtherysLocation(UUID worldUuid, Vector3d position, Vector3i blockPosition, Vector3i chunkPosition, Vector3i biomePosition) {
        this.worldUuid = worldUuid;
        this.position = position;
        this.blockPosition = blockPosition;
        this.chunkPosition = chunkPosition;
        this.biomePosition = biomePosition;
    }

    public UUID getWorldUUID() {
        return worldUuid;
    }

    public Vector3d getPosition() {
        return position;
    }

    public Vector3i getBlockPosition() {
        return blockPosition;
    }

    public Vector3i getChunkPosition() {
        return chunkPosition;
    }

    public Vector3i getBiomePosition() {
        return biomePosition;
    }

    /**
     * Creates a copy of this AtherysLocation object, with the new worldUuid.
     *
     * @param worldUuid the new worldUuid
     * @return A copy of this object with the different worldUuid
     */
    public AtherysLocation setWorldUuid(UUID worldUuid) {
        if (worldUuid == null) throw new IllegalArgumentException("worldUuid cannot be null.");
        return new AtherysLocation(
                worldUuid,
                position,
                blockPosition,
                chunkPosition,
                biomePosition
        );
    }

    /**
     * Creates a copy of this AtherysLocation object, with the new position.
     *
     * @param position the new position
     * @return A copy of this object with the different position
     */
    public AtherysLocation setPosition(Vector3d position) {
        if (position == null) throw new IllegalArgumentException("position cannot be null.");
        return new AtherysLocation(
                worldUuid,
                position,
                blockPosition,
                chunkPosition,
                biomePosition
        );
    }

    /**
     * Creates a copy of this AtherysLocation object, with the new blockPosition.
     *
     * @param blockPosition the new blockPosition
     * @return A copy of this object with the different blockPosition
     */
    public AtherysLocation setBlockPosition(Vector3i blockPosition) {
        if (blockPosition == null) throw new IllegalArgumentException("blockPosition cannot be null.");
        return new AtherysLocation(
                worldUuid,
                position,
                blockPosition,
                chunkPosition,
                biomePosition
        );
    }

    /**
     * Creates a copy of this AtherysLocation object, with the new chunkPosition.
     *
     * @param chunkPosition the new chunkPosition
     * @return A copy of this object with the different chunkPosition
     */
    public AtherysLocation setChunkPosition(Vector3i chunkPosition) {
        if (chunkPosition == null) throw new IllegalArgumentException("chunkPosition cannot be null.");
        return new AtherysLocation(
                worldUuid,
                position,
                blockPosition,
                chunkPosition,
                biomePosition
        );
    }

    /**
     * Creates a copy of this AtherysLocation object, with the new biomePosition.
     *
     * @param biomePosition the new biomePosition
     * @return A copy of this object with the different biomePosition
     */
    public AtherysLocation setBiomePosition(Vector3i biomePosition) {
        if (biomePosition == null) throw new IllegalArgumentException("biomePosition cannot be null.");
        return new AtherysLocation(
                worldUuid,
                position,
                blockPosition,
                chunkPosition,
                biomePosition
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AtherysLocation that = (AtherysLocation) o;
        return Objects.equals(worldUuid, that.worldUuid) &&
                Objects.equals(position, that.position) &&
                Objects.equals(blockPosition, that.blockPosition) &&
                Objects.equals(chunkPosition, that.chunkPosition) &&
                Objects.equals(biomePosition, that.biomePosition);
    }

    public boolean equals(Location<World> location) {
        return Objects.equals(location.getExtent().getUniqueId(), this.worldUuid) &&
                Objects.equals(location.getPosition(), this.position) &&
                Objects.equals(location.getBlockPosition(), this.blockPosition) &&
                Objects.equals(location.getChunkPosition(), this.chunkPosition) &&
                Objects.equals(location.getBiomePosition(), this.biomePosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(worldUuid, position, blockPosition, chunkPosition, biomePosition);
    }

    @Override
    public String toString() {
        return "AtherysLocation{" +
                "worldUuid=" + worldUuid +
                ", position=" + position +
                ", blockPosition=" + blockPosition +
                ", chunkPosition=" + chunkPosition +
                ", biomePosition=" + biomePosition +
                '}';
    }
}
