package org.virep.ShadowRealm.interfaces;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

import java.util.Collections;
import java.util.List;

public interface SlashCommand {
    String getName();
    SlashCommandData getCommandData();
    default List<Permission> getBotPermissions() {
        return Collections.emptyList();
    }
    void execute(SlashCommandInteractionEvent event);
}
