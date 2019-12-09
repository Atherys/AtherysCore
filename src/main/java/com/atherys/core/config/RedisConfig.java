package com.atherys.core.config;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public final class RedisConfig {

    @Setting("is-enabled")
    public boolean IS_REDIS_ENABLED = false;

    @Setting("redis-address")
    public String REDIS_ADDRESS = "localhost";

    @Setting("redis-port")
    public int REDIS_PORT = 6379;

}
