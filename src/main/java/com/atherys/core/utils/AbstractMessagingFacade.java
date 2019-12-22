package com.atherys.core.utils;

import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.channel.MessageReceiver;

import static org.spongepowered.api.text.format.TextColors.*;

/**
 * An abstraction to help facilitate messaging facades.
 */
public abstract class AbstractMessagingFacade {
    private Text prefix;

    public AbstractMessagingFacade(String prefix) {
        this.prefix = Text.of(GOLD, "[", DARK_GREEN, prefix, GOLD, "] ");
    }

    public Text formatInfo(Object... message) {
        return Text.of(prefix, DARK_GREEN, Text.of(message));
    }

    public Text formatError(Object... message) {
        return Text.of(prefix, RED, Text.of(message));
    }

    public void info(MessageReceiver receiver, Object... message) {
        receiver.sendMessage(formatInfo(message));
    }

    public void error(MessageReceiver receiver, Object... message) {
        receiver.sendMessage(formatError(message));
    }
}
