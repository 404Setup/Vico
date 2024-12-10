package one.tranic.vico.lib.message;

import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.title.Title;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
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
        if (message != null) sender.sendMessage(message);
    }

    @Override
    public void sendMessage(net.kyori.adventure.text.@Nullable Component message, @NotNull CommandSender sender) {
        if (message != null) adventure().sender(sender).sendMessage(message);
    }

    @Override
    public void showBossbar(@NotNull BossBar bossBar, @NotNull CommandSender sender) {
        if (sender instanceof Player) adventure().sender(sender).showBossBar(bossBar);
    }

    @Override
    public void showTitle(@NotNull Title title, @NotNull CommandSender sender) {
        if (sender instanceof Player) adventure().sender(sender).showTitle(title);
    }
}
