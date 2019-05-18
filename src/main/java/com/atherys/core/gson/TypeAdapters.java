package com.atherys.core.gson;

import com.google.gson.GsonBuilder;
import org.spongepowered.api.CatalogType;
import org.spongepowered.api.data.DataSerializable;

/**
 * A utility class for registering classes to a {@link GsonBuilder}.
 * TODO: Make more generic?
 */
public class TypeAdapters {

    @SafeVarargs
    public static void registerCatalogTypes(GsonBuilder builder, Class<? extends CatalogType>...classes) {
        for (Class<? extends CatalogType> cls : classes) {
            CatalogTypeAdapter<?> newAdapter = new CatalogTypeAdapter<>(cls);
            builder.registerTypeAdapter(cls, newAdapter);
        }
    }

    @SafeVarargs
    public static void registerSerializables(GsonBuilder builder, Class<? extends DataSerializable>...classes) {
        for (Class<? extends DataSerializable> cls : classes) {
            SerializableTypeAdapter<?> newAdapter = new SerializableTypeAdapter<>(cls);
            builder.registerTypeAdapter(cls, newAdapter);
        }
    }
}
