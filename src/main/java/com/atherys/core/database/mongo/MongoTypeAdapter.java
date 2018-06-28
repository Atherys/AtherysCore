package com.atherys.core.database.mongo;

import org.bson.Document;

import java.util.Optional;

public abstract class MongoTypeAdapter<T> {

    private Class<T> clazz;

    protected MongoTypeAdapter(Class<T> clazz) {
        this.clazz = clazz;
    }

    protected abstract Optional<Document> toDocument(T object);

    protected abstract Optional<T> fromDocument(Document doc);

    public boolean isApplicable(Object object) {
        return getApplicableClass().isAssignableFrom(object.getClass());
    }

    public Class<T> getApplicableClass() {
        return clazz;
    }
}
