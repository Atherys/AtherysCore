package com.atherys.core.utils;

import com.atherys.core.gson.ItemStackSnapshotAdapter;
import com.atherys.core.gson.TextTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.spongepowered.api.item.inventory.ItemStackSnapshot;
import org.spongepowered.api.text.Text;

public final class GsonUtils {

    public static Gson getGson() {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .registerTypeAdapter( Text.class, new TextTypeAdapter() )
                .registerTypeAdapter(ItemStackSnapshot.class, new ItemStackSnapshotAdapter() )
                .create();
    }

}
