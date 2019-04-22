package com.atherys.core.chat;

import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.channel.AbstractMutableMessageChannel;
import org.spongepowered.api.text.channel.MessageReceiver;
import org.spongepowered.api.text.chat.ChatType;

import java.util.Optional;

public class AbstractChatChannel extends AbstractMutableMessageChannel implements ChatChannel {
    private String channelId;
    private String permission;

    public AbstractChatChannel(String channelId) {
        this.channelId = channelId;
        this.permission = "atheryscore.chat." + channelId;
    }

    @Override
    public String getId() {
        return channelId;
    }

    @Override
    public String getPermission() {
        return permission;
    }

    @Override
    public Optional<Text> transformMessage(Object sender, MessageReceiver recipient, Text original, ChatType type) {
        return Optional.of(Text.of(getId(), " ", original));
    }
}
