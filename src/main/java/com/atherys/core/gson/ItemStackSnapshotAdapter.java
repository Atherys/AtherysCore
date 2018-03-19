package com.atherys.core.gson;

import com.atherys.core.AtherysCore;
import com.google.common.reflect.TypeToken;
import com.google.gson.JsonParser;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.gson.GsonConfigurationLoader;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import org.spongepowered.api.item.inventory.ItemStackSnapshot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class ItemStackSnapshotAdapter extends TypeAdapter<ItemStackSnapshot> {

    private JsonParser parser = new JsonParser();

    @Override
    public void write(JsonWriter out, ItemStackSnapshot value) throws IOException {
        GsonConfigurationLoader loader = GsonConfigurationLoader.builder().build();
        ConfigurationNode node = null;
        try {
            node = loader.createEmptyNode().setValue( TypeToken.of( ItemStackSnapshot.class ), value);
            StringWriter writer = new StringWriter();
            loader.saveInternal( node, writer);

            String json = writer.toString();

            AtherysCore.getInstance().getLogger().info( "Write: " + json );

            out.jsonValue( json );

        } catch (ObjectMappingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ItemStackSnapshot read(JsonReader in) throws IOException {
        String json = parser.parse(in).toString();

        AtherysCore.getInstance().getLogger().info( "Read: " + json );

        GsonConfigurationLoader loader = GsonConfigurationLoader.builder().setSource( () -> new BufferedReader( new StringReader( json ) ) ).build();
        ConfigurationNode node = loader.load();

        try {
            return node.getValue( TypeToken.of( ItemStackSnapshot.class ) );
        } catch (ObjectMappingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
