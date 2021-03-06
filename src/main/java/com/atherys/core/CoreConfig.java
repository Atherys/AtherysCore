package com.atherys.core;

import com.atherys.core.db.JPAConfig;
import com.atherys.core.utils.PluginConfig;
import ninja.leaping.configurate.objectmapping.Setting;

import java.io.IOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class CoreConfig extends PluginConfig {

    @Setting("combat-limit")
    public Duration COMBAT_LIMIT = Duration.of(30, ChronoUnit.SECONDS);

    @Setting("db-enabled")
    public boolean DB_ENABLED = true;

    @Setting("jpa")
    public JPAConfig JPA_CONFIG = new JPAConfig();

    protected CoreConfig() throws IOException {
        super("config/" + AtherysCore.ID, "config.conf");
    }
}
