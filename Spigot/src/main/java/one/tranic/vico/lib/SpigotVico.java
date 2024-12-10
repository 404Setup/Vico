package one.tranic.vico.lib;

import one.tranic.vico.lib.message.MessageSenderImpl;
import one.tranic.vico.lib.message.SpigotMessageSender;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class SpigotVico implements VicoImpl {
    private final MessageSenderImpl<CommandSender, Plugin> messageSender;

    public SpigotVico() {
        messageSender = new SpigotMessageSender();
        messageSender.adventure();
    }

    @Override
    public MessageSenderImpl<CommandSender, Plugin> getMessageSender() {
        return messageSender;
    }

    @Override
    public void close() {
        messageSender.close();
    }
}
