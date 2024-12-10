package one.tranic.vico.lib.message;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PaperMessageSender implements MessageSenderImpl<CommandSender, Plugin> {
    private Plugin plugin;

    @Override
    public void setPlugin(@NotNull Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void sendMessage(String message, @NotNull CommandSender sender) {
        if (message == null) return;
        sender.sendMessage(message);
    }

    @Override
    public void sendMessage(net.kyori.adventure.text.@Nullable Component message, @NotNull CommandSender sender) {
        if (message == null) return;
        sender.sendMessage(message);
    }
}
