package org.virep.ShadowRealm.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import org.virep.ShadowRealm.interfaces.SlashCommand;

@SuppressWarnings("unused")
public class PingCommand implements SlashCommand {

    @Override
    public String getName() {
        return "ping";
    }

    @Override
    public SlashCommandData getCommandData() {
        return Commands.slash(getName(), "pong lol");
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.getJDA().getRestPing().queue(ping ->
                event.replyFormat("**Pong!**\nGateway: %dms | REST: %dms", event.getJDA().getGatewayPing(), ping.intValue()).queue()
        );
    }
}