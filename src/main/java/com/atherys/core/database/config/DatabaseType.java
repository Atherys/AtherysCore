package com.atherys.core.database.config;

import org.spongepowered.api.CatalogType;
import org.spongepowered.api.util.annotation.CatalogedBy;

@CatalogedBy( DatabaseTypes.class )
public class DatabaseType implements CatalogType {

    private String id;
    private String name;

    DatabaseType(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }
}
