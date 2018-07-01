package com.atherys.core.script.lib.entity;

import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.text.Text;

import java.util.function.BiFunction;

public class SetEntityName implements BiFunction<Entity, Text, Boolean> {
    public Boolean apply(Entity entity, Text name){
        return entity.offer(Keys.DISPLAY_NAME, name).isSuccessful();
    }
}
