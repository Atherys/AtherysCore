package com.atherys.core.db;

import javax.annotation.Nonnull;
import java.util.UUID;

public interface SpongeIdentifiable extends Identifiable<UUID>, org.spongepowered.api.util.Identifiable {

    @Override
    @Nonnull
    default UUID getUniqueId() {
        return getId();
    }

}
