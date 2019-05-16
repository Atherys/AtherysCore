package com.atherys.core.gson;

import com.atherys.core.AtherysCore;
import com.google.common.reflect.TypeToken;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.gson.GsonConfigurationLoader;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Type;

public class AbstractConfigurateAdapter<T> extends TypeAdapter<T> implements
        JsonDeserializer<T>, JsonSerializer<T> {

    private Class<T> clazz;

    private JsonParser parser = new JsonParser();

    protected AbstractConfigurateAdapter(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void write(JsonWriter out, T value) throws IOException {
        GsonConfigurationLoader loader = GsonConfigurationLoader.builder().setLenient(true).setIndent(0).build();
        try {
            ConfigurationNode node = loader.createEmptyNode().setValue(TypeToken.of(clazz), value);
            StringWriter writer = new StringWriter();
            loader.saveInternal(node, writer);

            String json = writer.toString();

            AtherysCore.getInstance().getLogger().info("Write: " + json); // DEBUG

            out.jsonValue(json);

        } catch (ObjectMappingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public T read(JsonReader in) throws IOException {
        String json = parser.parse(in).toString();

        AtherysCore.getInstance().getLogger().info("Read: " + json); // DEBUG

        GsonConfigurationLoader loader = GsonConfigurationLoader.builder().setSource(() -> new BufferedReader(new StringReader(json))).build();
        ConfigurationNode node = loader.load();

        try {
            return node.getValue(TypeToken.of(clazz));
        } catch (ObjectMappingException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        String jsonString = json.toString();

        AtherysCore.getInstance().getLogger().info( "Read: " + jsonString ); // DEBUG

        try {

            GsonConfigurationLoader loader = GsonConfigurationLoader.builder()
                    .setSource(() -> new BufferedReader(new StringReader(jsonString))).build();
            ConfigurationNode node = loader.load();

            try {
                return node.getValue(TypeToken.of(clazz));
            } catch (ObjectMappingException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public JsonElement serialize(T src, Type typeOfSrc, JsonSerializationContext context) {
        GsonConfigurationLoader loader = GsonConfigurationLoader.builder().setLenient(true).setIndent(0)
                .build();
        try {
            ConfigurationNode node = loader.createEmptyNode().setValue(TypeToken.of(clazz), src);
            StringWriter writer = new StringWriter();
            loader.saveInternal(node, writer);

            String json = writer.toString();

            AtherysCore.getInstance().getLogger().info( "Write: " + json ); // DEBUG

            return parser.parse(json);

        } catch (ObjectMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static <R> void registerConfigurateAdapter(Class<R> clazz, GsonBuilder builder) {
        AbstractConfigurateAdapter<R> newAdapter = new AbstractConfigurateAdapter<>(clazz);
        builder.registerTypeAdapter(clazz, newAdapter);
    }
}
