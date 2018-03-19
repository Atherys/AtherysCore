package com.atherys.core.gson;

import com.google.gson.JsonParser;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.serializer.TextSerializers;

import java.io.IOException;

/**
 * A Gson {@link TypeAdapter} for Sponge's {@link Text}
 */
public class TextTypeAdapter extends TypeAdapter<Text> {

    static JsonParser parser = new JsonParser();

    @Override
    public void write( JsonWriter out, Text value ) throws IOException {
        String textJson = TextSerializers.JSON.serialize( value );
        //AtherysCore.getInstance().getLogger().info( "Write: " + textJson ); // DEBUG
        out.jsonValue( textJson );
    }

    @Override
    public Text read( JsonReader in ) throws IOException {
        String textJson = parser.parse( in ).toString();
        //AtherysCore.getInstance().getLogger().info( "Read: " + textJson ); // DEBUG
        return TextSerializers.JSON.deserialize( textJson );
    }

}
