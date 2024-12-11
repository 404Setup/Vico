package one.tranic.vico.lib;

import one.tranic.vico.lib.message.MessageSenderImpl;
import one.tranic.vico.lib.message.SpigotMessageSender;
import one.tranic.vico.lib.player.PluginPlayerImpl;
import one.tranic.vico.lib.player.SpigotPlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class SpigotVico implements VicoImpl {
    private final MessageSenderImpl<CommandSender, Plugin> messageSender;
    private final PluginPlayerImpl player;

    public SpigotVico() {
        messageSender = new SpigotMessageSender();
        messageSender.adventure();
        player = new SpigotPlayer();
    }

    @Override
    public MessageSenderImpl<CommandSender, Plugin> getMessageSender() {
        return messageSender;
    }

    @Override
    public PluginPlayerImpl getPluginPlayer() {
        return player;
    }

    @Override
    public void close() {
        messageSender.close();
    }
}
