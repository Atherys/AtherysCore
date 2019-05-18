package com.atherys.core.gson;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;

public abstract class AbstractTypeAdapter<T> implements JsonDeserializer<T>, JsonSerializer<T> {
    protected Class<T> clazz;

    protected AbstractTypeAdapter(Class<T> clazz) {
        this.clazz = clazz;
    }
}
