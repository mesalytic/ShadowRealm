package org.virep.ShadowRealm;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.virep.ShadowRealm.listeners.MessageListener;

public class Main {
    public static void main(String[] args) throws Exception {
        JDA jda = JDABuilder.createDefault(Config.get("TOKEN")).build();

        jda.addEventListener(new MessageListener());
    }
}