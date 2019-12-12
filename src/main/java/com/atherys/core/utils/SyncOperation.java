package com.atherys.core.utils;

import org.spongepowered.api.scheduler.Task;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Supplier;

public final class SyncOperation {

    public static void run(Object plugin, Runnable operation) {
        Task.builder()
                .execute(operation)
                .submit(plugin);
    }

    public static <R> void fetchAndHandle(Object plugin, Supplier<R> fetch, Consumer<R> handle) {
        run(plugin, () -> {
            R result = fetch.get();
            CompletableFuture.runAsync(() -> handle.accept(result));
        });
    }

}
