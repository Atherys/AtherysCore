package com.atherys.core.chat;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ChatChannelService {
    private Map<String, ChatChannel> chatChannels = new HashMap<>();

    public void registerChannel(ChatChannel channel) {
        chatChannels.put(channel.getId(), channel);
    }

    public Optional<ChatChannel> getChannel(String id) {
        return Optional.ofNullable(chatChannels.get(id));
    }

    public Collection<ChatChannel> getChannels() {
        return chatChannels.values();
    }
}
