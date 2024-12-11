package one.tranic.vico.lib.message;

import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

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

    private void kickPlayer(@Nullable Player player, @Nullable Component reason) {
        kickPlayer(player, LegacyComponentSerializer.legacySection().serialize(reason == null ? kickReason : reason));
    }

    private void kickPlayer(@Nullable Player player, @Nullable String reason) {
        if (player != null) player.kickPlayer(reason == null ? kickReasonStr : reason);
    }

    @Override
    public void kickPlayer(@Nullable Component reason, @NotNull UUID target) {
        kickPlayer(Bukkit.getPlayer(target), reason);
    }

    @Override
    public void kickPlayer(@Nullable String reason, @NotNull UUID target) {
        kickPlayer(Bukkit.getPlayer(target), reason);
    }

    @Override
    public void kickPlayer(@Nullable Component reason, @NotNull String target) {
        kickPlayer(Bukkit.getPlayer(target), reason);
    }

    @Override
    public void kickPlayer(@Nullable String reason, @NotNull String target) {
        kickPlayer(Bukkit.getPlayer(target), reason);
    }
}
