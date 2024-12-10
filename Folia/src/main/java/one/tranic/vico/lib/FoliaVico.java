package one.tranic.vico.lib;

import one.tranic.vico.lib.message.MessageSenderImpl;
import one.tranic.vico.lib.message.PaperMessageSender;
import one.tranic.vico.lib.player.FoliaPlayer;
import one.tranic.vico.lib.player.PluginPlayerImpl;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class FoliaVico implements VicoImpl {
    private final MessageSenderImpl<CommandSender, Plugin> messageSender;
    private final PluginPlayerImpl player;

    public FoliaVico() {
        this.messageSender = new PaperMessageSender();
        this.player = new FoliaPlayer();
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
