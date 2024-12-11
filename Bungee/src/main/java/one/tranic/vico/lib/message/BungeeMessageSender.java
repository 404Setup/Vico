package one.tranic.vico.lib.message;

import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.platform.bungeecord.BungeeAudiences;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.title.Title;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;
import one.tranic.vico.app.BungeeApp;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

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
        if (message != null) sender.sendMessage(message);
    }

    @Override
    public void sendMessage(@Nullable Component message, @NotNull CommandSender sender) {
        if (message != null) adventure().sender(sender).sendMessage(message);
    }

    @Override
    public void showBossbar(@NotNull BossBar bossBar, @NotNull CommandSender sender) {
        if (sender instanceof ProxiedPlayer) adventure().sender(sender).showBossBar(bossBar);
    }

    @Override
    public void showTitle(@NotNull Title title, @NotNull CommandSender sender) {
        if (sender instanceof ProxiedPlayer) adventure().sender(sender).showTitle(title);
    }

    private void kickPlayer(@Nullable ProxiedPlayer player, @Nullable Component reason) {
        if (player == null) return;
        player.disconnect(LegacyComponentSerializer.legacySection().serialize(reason == null ? kickReason : reason));
    }

    private void kickPlayer(@Nullable ProxiedPlayer player, @Nullable String reason) {
        if (player == null) return;
        player.disconnect(reason == null ? kickReasonStr : reason);
    }

    @Override
    public void kickPlayer(@Nullable Component reason, @NotNull UUID target) {
        kickPlayer(BungeeApp.getInstance().getProxy().getPlayer(target), reason);
    }

    @Override
    public void kickPlayer(@Nullable String reason, @NotNull UUID target) {
        kickPlayer(BungeeApp.getInstance().getProxy().getPlayer(target), reason);
    }

    @Override
    public void kickPlayer(@Nullable Component reason, @NotNull String target) {
        kickPlayer(BungeeApp.getInstance().getProxy().getPlayer(target), reason);
    }

    @Override
    public void kickPlayer(@Nullable String reason, @NotNull String target) {
        kickPlayer(BungeeApp.getInstance().getProxy().getPlayer(target), reason);
    }

    @Override
    public void close() {
        if (adventure != null) {
            adventure.close();
            adventure = null;
        }
    }
}
