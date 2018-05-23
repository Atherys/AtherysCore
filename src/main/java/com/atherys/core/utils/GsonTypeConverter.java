package com.atherys.core.utils;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import org.mongodb.morphia.converters.TypeConverter;
import org.mongodb.morphia.mapping.MappedField;

public class GsonTypeConverter<T> extends TypeConverter {

    private Gson gson;
    private Class<T> clazz;

    public GsonTypeConverter ( Class<T> clazz, Gson gson ) {
        super(clazz);
        this.clazz = clazz;
        this.gson = gson;
    }

    @Override
    public Object decode(Class<?> targetClass, Object fromDBObject, MappedField optionalExtraInfo) {
        BasicDBObject dbObject = (BasicDBObject) fromDBObject;
        return gson.fromJson(dbObject.toJson(), clazz);
    }

    @Override
    public Object encode(Object object, MappedField optionalExtraInfo) {
        return gson.toJson(object);
    }
}
