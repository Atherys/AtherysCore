package com.atherys.core.utils;

import com.flowpowered.math.vector.Vector3d;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.scheduler.Task;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.channel.MessageReceiver;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * A wrapper around {@link SyncOperation} which contains a plugin object.
 * Also contains additional utility methods for ease-of-use.
 */
public class SyncOperator {

    private Object plugin;

    public SyncOperator(Object plugin) {
        this.plugin = plugin;
    }

    /**
     * Run an operation on the main minecraft thread.
     *
     * @param operation The operation to be executed
     */
    public void run(Runnable operation) {
        Task.builder()
                .execute(operation)
                .submit(plugin);
    }

    /**
     * Fetch a value from the main minecraft thread and handle it asynchronously.
     * If any exceptions occur, the handler will not be called.
     *
     * @param fetch  How the value is to be fetched
     * @param handle How the value is to be handled
     * @param <R>    The type of the value being handled
     */
    public <R> void fetchAndHandle(Supplier<R> fetch, Consumer<R> handle) {
        fetch(fetch).ifPresent((result) -> CompletableFuture.runAsync(() -> handle.accept(result)));
    }

    /**
     * Fetch a value from the main minecraft thread, and block the calling thread until it is available.
     *
     * @param fetch  How the value is to be fetched
     * @param <R>    The type of the value being handled
     * @return The result. Will be an empty optional if an exception occurs.
     */
    public <R> Optional<R> fetch(Supplier<R> fetch) {
        return fetchExceptionally(fetch, (e) -> null);
    }

    /**
     * Fetch a value from the main minecraft thread, and block the calling thread until it is available.
     *
     * @param fetch            How the value is to be fetched
     * @param exceptionHandler What to do in case of any exception being thrown
     * @param <R>              The type of the value being handled
     * @return The result
     */
    public <R> Optional<R> fetchExceptionally(Supplier<R> fetch, Function<Throwable, ? extends R> exceptionHandler) {
        CompletableFuture<R> future = new CompletableFuture<>();

        run(() -> future.complete(fetch.get()));

        return Optional.ofNullable(future.exceptionally(exceptionHandler).join());
    }

    public void sendMessage(MessageReceiver messageReceiver, Text message) {
        run(() -> messageReceiver.sendMessage(message));
    }

    public void playSound(Player player, Sound sound, Vector3d position) {
        run(() -> Sound.playSound(sound, player, position));
    }
}
