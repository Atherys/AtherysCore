package com.atherys.core.database.api;

import org.spongepowered.api.util.Identifiable;

import java.util.UUID;

/**
 * A base interface to be implemented by any classes intending to be saved by a {@link
 * DatabaseManager}
 */
public interface DBObject extends Identifiable {

    /**
     * The UUID of this object. All UUIDs must be unique to the object itself. No shared UUIDs between
     * objects.
     *
     * @return The UUID.
     */
    default UUID getUUID() {
        return getUniqueId();
    }
}
