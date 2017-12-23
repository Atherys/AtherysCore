package com.atherys.core.data;

import org.bson.Document;

public interface DataAdapter<T> {

    T fromDocument ( Document document );

    Document toDocument ( T object );

}
