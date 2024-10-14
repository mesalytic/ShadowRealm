package org.virep.ShadowRealm.handlers;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import org.reflections.Reflections;
import org.virep.ShadowRealm.Config;
import org.virep.ShadowRealm.interfaces.SlashCommand;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SlashCommandHandler {
    private final JDA jda;
    public static final Map<String, SlashCommand> slashCommandMap = new HashMap<>();

    public SlashCommandHandler(JDA jda) {
        this.jda = jda;
    }

public void addCommands() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    final CommandListUpdateAction update;

    if (Boolean.parseBoolean(System.getProperty("dev"))) {
        Guild guild = jda.getGuildById(Config.get("DEV_SERVER_ID"));
        update = guild.updateCommands();
    } else {
        update = jda.updateCommands();
    }

    Reflections reflectionCommands = new Reflections("org.virep.ShadowRealm.commands");
    Set<Class<? extends SlashCommand>> commandClasses = reflectionCommands.getSubTypesOf(SlashCommand.class);

    for (Class<? extends SlashCommand> commandClass : commandClasses) {
        if (Modifier.isAbstract(commandClass.getModifiers())) continue;

        SlashCommand command = commandClass.getConstructor().newInstance();
        update.addCommands(command.getCommandData());
        slashCommandMap.put(command.getName(), command);
    }

    update.queue();
}

    public Map<String, SlashCommand> getSlashCommandMap() {
        return slashCommandMap;
    }
}
