package org.virep.ShadowRealm.listeners;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.virep.ShadowRealm.handlers.SlashCommandHandler;
import org.virep.ShadowRealm.interfaces.SlashCommand;

import java.util.Map;

public class SlashCommandListener extends ListenerAdapter {
    private final SlashCommandHandler slashCommandHandler;

    public SlashCommandListener(SlashCommandHandler slashCommandHandler) {
        this.slashCommandHandler = slashCommandHandler;
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String commandName = event.getName();
        Map<String, SlashCommand> commandMap = slashCommandHandler.getSlashCommandMap();

        if (commandMap.containsKey(commandName)) {
            SlashCommand command = commandMap.get(commandName);

            command.execute(event);
        }
    }
}
