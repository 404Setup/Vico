package one.tranic.vico.lib.message;

import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SpigotMessageSender implements MessageSenderImpl<CommandSender, Plugin> {
    private BukkitAudiences adventure;
    private Plugin plugin;

    @Override
    public @NotNull BukkitAudiences adventure() {
        if (adventure == null) adventure = BukkitAudiences.create(plugin);
        return adventure;
    }

    @Override
    public void setPlugin(@NotNull Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void close() {
        if (adventure != null) {
            adventure.close();
            adventure = null;
        }
    }

    @Override
    public void sendMessage(String message, @NotNull CommandSender sender) {
        if (message == null) return;
        sender.sendMessage(message);
    }

    @Override
    public void sendMessage(net.kyori.adventure.text.@Nullable Component message, @NotNull CommandSender sender) {
        if (message == null) return;
        adventure().sender(sender).sendMessage(message);
    }
}
