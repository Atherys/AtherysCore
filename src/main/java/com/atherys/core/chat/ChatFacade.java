package com.atherys.core.chat;

import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.channel.MessageChannel;
import org.spongepowered.api.text.channel.MessageReceiver;

import java.util.Collection;

public class ChatFacade {
    private ChatChannelService channelService;

    public ChatFacade(ChatChannelService service) {
        this.channelService = service;
    }

    public void joinChatChannel(CommandSource src, String channelId) {
        channelService.getChannel(channelId).ifPresent(channel -> {
            if (channel.canChat(src)) {
                channel.addMember(src);
            }
        });
    }

    public void toggleChatChannel(CommandSource src, String channelId) {
        channelService.getChannel(channelId).ifPresent(channel -> {
            if (channel.canChat(src)) {
                channel.addMember(src);
                src.setMessageChannel(channel);
            }
        });
    }

    public void leaveChatChannel(CommandSource src, String channelId) {
        channelService.getChannel(channelId).ifPresent(channel -> {
            channel.removeMember(src);
        });
    }

    public void focusChatChannel(CommandSource src, String channelId) {
        channelService.getChannels().forEach(channel -> channel.removeMember(src));
        joinChatChannel(src, channelId);
    }

    public void onPlayerChat(ChatChannel channel, CommandSource source, Text message) {
        Collection<MessageReceiver> members = channel.collectMembers(source);
        MessageChannel.fixed(members).send(message);
    }
}
