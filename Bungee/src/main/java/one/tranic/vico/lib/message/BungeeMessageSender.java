package one.tranic.vico.lib.message;

import net.kyori.adventure.platform.bungeecord.BungeeAudiences;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BungeeMessageSender implements MessageSenderImpl<CommandSender, Plugin> {
    private BungeeAudiences adventure;
    private Plugin plugin;

    @Override
    public @NotNull BungeeAudiences adventure() {
        if (adventure == null) adventure = BungeeAudiences.create(plugin);
        return adventure;
    }

    @Override
    public void setPlugin(@NotNull Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void sendMessage(@Nullable String message, @NotNull CommandSender sender) {
        if (message == null) return;
        sender.sendMessage(message);
    }

    @Override
    public void sendMessage(@Nullable Component message, @NotNull CommandSender sender) {
        if (message == null) return;
        adventure().sender(sender).sendMessage(message);
    }

    @Override
    public void close() {
        if (adventure != null) {
            adventure.close();
            adventure = null;
        }
    }
}
