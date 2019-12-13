package com.atherys.core.utils;

import org.spongepowered.api.scheduler.Task;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public final class SyncOperation {

    /**
     * Run an operation on the main minecraft thread.
     *
     * @param plugin    The plugin from which this operation originates
     * @param operation The operation to be executed
     */
    public static void run(Object plugin, Runnable operation) {
        Task.builder()
                .execute(operation)
                .submit(plugin);
    }

    /**
     * Fetch a value from the main minecraft thread and handle it asynchronously.
     * If any exceptions occur, the handler will not be called.
     *
     * @param plugin The plugin from which this operation originates
     * @param fetch  How the value is to be fetched
     * @param handle How the value is to be handled
     * @param <R>    The type of the value being handled
     */
    public static <R> void fetchAndHandle(Object plugin, Supplier<R> fetch, Consumer<R> handle) {
        fetch(plugin, fetch).ifPresent((result) -> CompletableFuture.runAsync(() -> handle.accept(result)));
    }

    /**
     * Fetch a value from the main minecraft thread, and block the calling thread until it is available.
     *
     * @param plugin The plugin from which this operation originates
     * @param fetch  How the value is to be fetched
     * @param <R>    The type of the value being handled
     * @return The result. Will be an empty optional if an exception occurs.
     */
    public static <R> Optional<R> fetch(Object plugin, Supplier<R> fetch) {
        return fetchExceptionally(plugin, fetch, (e) -> null);
    }

    /**
     * Fetch a value from the main minecraft thread, and block the calling thread until it is available.
     *
     * @param plugin           The plugin from which this operation originates
     * @param fetch            How the value is to be fetched
     * @param exceptionHandler What to do in case of any exception being thrown
     * @param <R>              The type of the value being handled
     * @return The result
     */
    public static <R> Optional<R> fetchExceptionally(Object plugin, Supplier<R> fetch, Function<Throwable, ? extends R> exceptionHandler) {
        CompletableFuture<R> future = new CompletableFuture<>();

        run(plugin, () -> {
            future.complete(fetch.get());
        });

        return Optional.ofNullable(future.exceptionally(exceptionHandler).join());
    }

}
