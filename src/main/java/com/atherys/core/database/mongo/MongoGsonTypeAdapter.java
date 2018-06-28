package com.atherys.core.database.mongo;

import com.google.gson.Gson;
import org.bson.Document;

import java.util.Optional;

public class MongoGsonTypeAdapter<T> extends MongoTypeAdapter<T> {

    private Gson gson;

    protected MongoGsonTypeAdapter(Class<T> clazz, Gson gson) {
        super(clazz);
    }

    @Override
    protected Optional<Document> toDocument(T object) {
        return Optional.ofNullable(Document.parse(gson.toJson(object)));
    }

    @Override
    protected Optional<T> fromDocument(Document doc) {
        String json = doc.toJson();
        return Optional.ofNullable(gson.fromJson(json, super.getApplicableClass()));
    }
}
