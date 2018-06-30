package com.atherys.core.script;

import com.atherys.core.AtherysCore;
import com.atherys.core.script.lib.ScriptLibrary;
import com.atherys.core.script.lib.block.BlockLibrary;
import com.atherys.core.script.lib.damage.DamageLibrary;
import com.atherys.core.script.lib.entity.EntityLibrary;
import com.atherys.core.script.lib.item.ItemStackLibrary;
import com.atherys.core.script.lib.location.LocationLibrary;
import com.atherys.core.script.lib.player.PlayerLibrary;
import com.atherys.core.script.lib.potion.PotionEffectLibrary;
import com.atherys.core.script.lib.task.TaskLibrary;
import com.atherys.core.script.lib.text.TextLibrary;
import com.atherys.core.script.lib.util.UtilityLibrary;

import javax.script.ScriptEngine;

public final class CoreLibrary implements ScriptLibrary {
    @Override
    public void registerObjects(ScriptEngine engine) {
        new EntityLibrary().registerObjects(engine);
        new DamageLibrary().registerObjects(engine);
        new PotionEffectLibrary().registerObjects(engine);
        new ItemStackLibrary().registerObjects(engine);
        new TextLibrary().registerObjects(engine);
        new PlayerLibrary().registerObjects(engine);
        new LocationLibrary().registerObjects(engine);
        new TaskLibrary().registerObjects(engine);
        new BlockLibrary().registerObjects(engine);
        new UtilityLibrary().registerObjects(engine);

        engine.put("CORE_PLUGIN", AtherysCore.getInstance());
    }
}
