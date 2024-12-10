package one.tranic.vico.lib;

import one.tranic.vico.lib.message.MessageSenderImpl;
import one.tranic.vico.lib.message.PaperMessageSender;
import one.tranic.vico.lib.player.PluginPlayerImpl;
import one.tranic.vico.lib.player.PaperPlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class PaperVico implements VicoImpl {
    private final MessageSenderImpl<CommandSender, Plugin> messageSender;
    private final PluginPlayerImpl player;

    public PaperVico() {
        this.messageSender = new PaperMessageSender();
        this.player = new PaperPlayer();
    }

    @Override
    public MessageSenderImpl<CommandSender, Plugin> getMessageSender() {
        return messageSender;
    }

    @Override
    public PluginPlayerImpl getPlayer() {
        return player;
    }
}
