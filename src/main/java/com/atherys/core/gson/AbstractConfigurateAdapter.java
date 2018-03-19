package com.atherys.core.gson;

import com.google.common.reflect.TypeToken;
import com.google.gson.JsonParser;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.gson.GsonConfigurationLoader;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public abstract class AbstractConfigurateAdapter<T> extends TypeAdapter<T> {

    private Class<T> clazz;

    private JsonParser parser = new JsonParser();

    protected AbstractConfigurateAdapter ( Class<T> clazz ) {
        this.clazz = clazz;
    }

    @Override
    public void write( JsonWriter out, T value ) throws IOException {
        GsonConfigurationLoader loader = GsonConfigurationLoader.builder().build();
        try {
            ConfigurationNode node = loader.createEmptyNode().setValue( TypeToken.of( clazz ), value );
            StringWriter writer = new StringWriter();
            loader.saveInternal( node, writer );

            String json = writer.toString();

            //therysCore.getInstance().getLogger().info( "Write: " + json ); // DEBUG

            out.jsonValue( json );

        } catch ( ObjectMappingException e ) {
            e.printStackTrace();
        }
    }

    @Override
    public T read( JsonReader in ) throws IOException {
        String json = parser.parse( in ).toString();

        //AtherysCore.getInstance().getLogger().info( "Read: " + json ); // DEBUG

        GsonConfigurationLoader loader = GsonConfigurationLoader.builder().setSource( () -> new BufferedReader( new StringReader( json ) ) ).build();
        ConfigurationNode node = loader.load();

        try {
            return node.getValue( TypeToken.of( clazz ) );
        } catch ( ObjectMappingException e ) {
            e.printStackTrace();
        }

        return null;
    }
}
