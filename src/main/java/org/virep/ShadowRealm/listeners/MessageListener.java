package org.virep.ShadowRealm.listeners;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

// Warning:(8, 51) Exception 'java.lang.Exception' is never thrown in the method
public class MessageListener extends ListenerAdapter {
    public void onMessageReceived(MessageReceivedEvent event) {
        Message message = event.getMessage();
        User author = message.getAuthor();

        if (author.getId().equals("604779545018761237")) {
            MessageChannel channel = event.getChannel();

            channel.sendMessage("test").queue();
        }
    }
}
