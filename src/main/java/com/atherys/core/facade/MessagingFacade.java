package com.atherys.core.facade;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.channel.MessageReceiver;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextFormat;

import java.util.Collection;

public interface MessagingFacade {

    default Text formatInfo(Object... msg) {
        return Text.of(getPrefix(), getInfoFormat(), Text.of(msg));
    }

    default Text formatError(Object... msg) {
        return Text.of(getPrefix(), getErrorFormat(), Text.of(msg));
    }

    default void info(MessageReceiver messageReceiver, Object... msg) {
        messageReceiver.sendMessage(formatInfo(msg));
    }

    default void info(Collection<MessageReceiver> receivers, Object... msg) {
        receivers.forEach(receiver -> this.info(receiver, msg));
    }

    default void broadcastInfo(Object... msg) {
        Sponge.getServer().getBroadcastChannel().send(formatInfo(msg));
    }

    default void error(MessageReceiver messageReceiver, Object... msg) {
        messageReceiver.sendMessage(formatError(msg));
    }

    default void error(Collection<MessageReceiver> receivers, Object... msg) {
        receivers.forEach(receiver -> this.error(receiver, msg));
    }

    default void broadcastError(Object... msg) {
        Sponge.getServer().getBroadcastChannel().send(formatError(msg));
    }

    default TextFormat getInfoFormat() {
        return TextFormat.of(TextColors.GREEN);
    }

    default TextFormat getErrorFormat() {
        return TextFormat.of(TextColors.RED);
    }

    Text getPrefix();

}
