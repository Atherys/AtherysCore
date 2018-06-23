package com.atherys.core.utils;

import org.mongodb.morphia.converters.TypeConverter;
import org.mongodb.morphia.mapping.MappedField;

public abstract class MorphiaTypeConverter<T> extends TypeConverter {

    @Override
    public Object decode(Class<?> targetClass, Object fromDBObject, MappedField optionalExtraInfo) {
        return deserialize(targetClass, fromDBObject, optionalExtraInfo);
    }

    @Override
    public Object encode(Object object, MappedField optionalExtraInfo) {
        return serialize((T) object, optionalExtraInfo);
    }

    @Override
    public boolean isSupported(final Class<?> clazz, MappedField optionalExtraInfo) {
        return isSupported(clazz);
    }

    protected abstract T deserialize(Class<?> target, Object from, MappedField optional);

    protected abstract Object serialize(T object, MappedField optional);

    protected abstract boolean isSupported(Class<?> clazz);
}
