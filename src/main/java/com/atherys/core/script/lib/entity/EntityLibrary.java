package com.atherys.core.script.lib.entity;

import com.atherys.core.script.lib.ScriptLibrary;

import javax.script.ScriptEngine;

public final class EntityLibrary implements ScriptLibrary {
    @Override
    public void registerObjects(ScriptEngine engine) {
        engine.put("createEntity", new CreateEntity());
        engine.put("spawnEntity", new SpawnEntity());
        engine.put("healEntity", new HealEntity());
        engine.put("damageEntity", new DamageEntity());
        engine.put("addEntityPotionEffect", new AddEntityPotionEffect());
        engine.put("setEntityPotionEffects", new SetEntityPotionEffects());
        engine.put("getEntityPotionEffects", new GetEntityPotionEffects());
        engine.put("getEntityHealth", new GetEntityHealth());
        engine.put("getEntityBoots", new GetEntityBoots());
        engine.put("getEntityChestplate", new GetEntityChestplate());
        engine.put("getEntityHelmet", new GetEntityHelmet());
        engine.put("getEntityLeggings", new GetEntityLeggings());
        engine.put("getEntityMainHand", new GetEntityMainHand());
        engine.put("getEntityOffHand", new GetEntityOffhand());
        engine.put("getEntityHealth", new GetEntityHealth());
        engine.put("getEntityMaxHealth", new GetEntityMaxHealth());
        engine.put("getEntityUUID", new GetEntityUUID());
        engine.put("getEntityName", new GetEntityName());
        engine.put("setEntityBoots", new SetEntityBoots());
        engine.put("setEntityChestplate", new SetEntityChestplate());
        engine.put("setEntityHelmet", new SetEntityHelmet());
        engine.put("setEntityLeggings", new SetEntityLeggings());
        engine.put("setEntityMainHand", new SetEntityMainHand());
        engine.put("setEntityOffHand", new SetEntityOffhand());
        engine.put("setEntityMaxHealth", new SetEntityMaxHealth());
        engine.put("setEntityName", new SetEntityName());
    }
}
