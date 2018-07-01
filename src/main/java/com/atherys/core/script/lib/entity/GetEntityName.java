package com.atherys.core.script.lib.entity;

import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.manipulator.mutable.DisplayNameData;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.text.Text;

import java.util.function.Function;

public class GetEntityName implements Function<Entity, Text> {
    public Text apply(Entity entity){
        return entity.get(Keys.DISPLAY_NAME).get();
    }
}
