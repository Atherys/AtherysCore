package com.atherys.core.utils;

import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataQuery;
import org.spongepowered.api.data.DataView;
import org.spongepowered.api.data.persistence.DataFormats;
import org.spongepowered.api.item.inventory.ItemStack;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

public final class DataUtils {

    public static final String dataViewToNBTString(DataView view) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            DataFormats.NBT.writeTo(outputStream, view);
        } catch (IOException e) {
            return "IOException while writing DataView NBT to String.";
        }

        return new String(outputStream.toByteArray(), Charset.defaultCharset());
    }

    public static final ItemStack writeUnsafeDataToItemStack(ItemStack itemStack, Object value, String... query) {
        DataContainer container = itemStack.toContainer();

        List<String> queryList = Arrays.asList(query);
        queryList.add(0, "UnsafeData");

        container.set(DataQuery.of(queryList), value);

        return ItemStack.builder().fromContainer(container).build();
    }

}
