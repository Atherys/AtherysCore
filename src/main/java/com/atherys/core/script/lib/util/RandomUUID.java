package com.atherys.core.script.lib.util;

import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;

public class RandomUUID implements Supplier<UUID> {
    @Override
    public UUID get() {
        return UUID.randomUUID();
    }
}
