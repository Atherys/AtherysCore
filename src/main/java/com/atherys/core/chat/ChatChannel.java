package com.atherys.core.chat;

import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.text.channel.MutableMessageChannel;

public interface ChatChannel extends MutableMessageChannel {
    String getId();

    String getPermission();

    default boolean canChat(CommandSource receiver) {
        return receiver.hasPermission(getPermission());
    }
}
