package com.atherys.core.damage;

import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.event.cause.entity.damage.DamageType;
import org.spongepowered.api.event.cause.entity.damage.source.DamageSource;
import org.spongepowered.api.util.annotation.CatalogedBy;

import java.util.function.Function;

@CatalogedBy( AtherysDamageTypes.class )
public class AtherysDamageType implements DamageType {

    private final String id;
    private final String name;

    private Function<Entity,DamageSource> asSource;

    AtherysDamageType (String id, String name, Function<Entity,DamageSource> asSource ) {
        this.id = id;
        this.name = name;
        this.asSource = asSource;
        AtherysDamageTypeRegistry.getInstance().flags.put( id, this );
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public DamageSource asSource( Entity source ) {
        return asSource.apply( source );
    }
}
