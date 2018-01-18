package com.atherys.core.damage;

import org.spongepowered.api.event.cause.entity.damage.DamageType;
import org.spongepowered.api.util.annotation.CatalogedBy;

@CatalogedBy( AtherysDamageTypes.class )
public class AtherysDamageType implements DamageType {

    private final String id;
    private final String name;

    AtherysDamageType ( String id, String name ) {
        this.id = id;
        this.name = name;
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
}
