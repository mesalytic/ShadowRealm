package org.virep.ShadowRealm;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import org.virep.ShadowRealm.handlers.SlashCommandHandler;
import org.virep.ShadowRealm.listeners.SlashCommandListener;

public class Main {
    public static void main(String[] args) throws Exception {
        JDA jda = JDABuilder
                .createDefault(Config.get("TOKEN"))
                .build()
                .awaitReady();

        jda.getPresence().setActivity(Activity.playing("test"));

        SlashCommandHandler slashCommandHandler = new SlashCommandHandler(jda);
        jda.addEventListener(new SlashCommandListener(slashCommandHandler));

        slashCommandHandler.addCommands();
    }
}