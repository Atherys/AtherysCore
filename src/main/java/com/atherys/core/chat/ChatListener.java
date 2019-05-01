package com.atherys.core.chat;

import com.atherys.core.AtherysCore;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.filter.cause.Root;
import org.spongepowered.api.event.message.MessageChannelEvent;

public class ChatListener {
    @Listener
    public void onPlayerChat(MessageChannelEvent.Chat event, @Root CommandSource source) {
        if (event.getChannel().isPresent() && event.getChannel().get() instanceof ChatChannel) {
            AtherysCore.getChatFacade().onPlayerChat((ChatChannel) event.getChannel().get(), source, event.getMessage());
            event.setCancelled(true);
        }
    }
}
