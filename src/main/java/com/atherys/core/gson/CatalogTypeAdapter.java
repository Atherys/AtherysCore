package com.atherys.core.gson;

import com.google.gson.*;
import org.spongepowered.api.CatalogType;
import org.spongepowered.api.Sponge;

import java.lang.reflect.Type;
import java.util.Optional;

public class CatalogTypeAdapter<T extends CatalogType> extends AbstractTypeAdapter<T> {
    protected CatalogTypeAdapter(Class<T> clazz) {
        super(clazz);
    }

    @Override
    public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Optional<T> type = Sponge.getRegistry().getType(clazz, json.getAsJsonPrimitive().getAsString());
        return type.orElse(null);
    }

    @Override
    public JsonElement serialize(T src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getId());
    }

    @SafeVarargs
    public static void registerAdaptersFor(GsonBuilder builder, Class<? extends CatalogType>...classes) {
        for (Class<? extends CatalogType> cls : classes) {
            CatalogTypeAdapter<?> newAdapter = new CatalogTypeAdapter<>(cls);
            builder.registerTypeAdapter(cls, newAdapter);
        }
    }
}
