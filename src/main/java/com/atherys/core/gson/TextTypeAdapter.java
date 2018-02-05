package com.atherys.core.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.serializer.TextSerializers;

import java.io.IOException;

public class TextTypeAdapter extends TypeAdapter<Text> {

    @Override
    public void write(JsonWriter out, Text value) throws IOException {
        out.beginObject();
            out.jsonValue( TextSerializers.JSON.serialize(value) );
        out.endObject();
    }

    @Override
    public Text read(JsonReader in) throws IOException {
        in.beginObject();
            String textJson = in.toString();
        in.endObject();

        return TextSerializers.JSON.deserialize( textJson );
    }

}
