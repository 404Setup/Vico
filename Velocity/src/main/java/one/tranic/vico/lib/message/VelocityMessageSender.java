package one.tranic.vico.lib.message;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import one.tranic.vico.app.VelocityApp;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.UUID;

public class VelocityMessageSender implements MessageSenderImpl<CommandSource, Object> {
    @Override
    public void sendMessage(@Nullable String message, @NotNull CommandSource sender){
        if (message != null) sender.sendMessage(Component.text(message));
    }

    @Override
    public void sendMessage(@Nullable Component message, @NotNull CommandSource sender) {
        if (message != null) sender.sendMessage(message);
    }

    @Override
    public void showBossbar(@NotNull BossBar bossBar, @NotNull CommandSource sender) {
        if (sender instanceof Player) sender.showBossBar(bossBar);
    }

    @Override
    public void showTitle(@NotNull Title title, @NotNull CommandSource sender) {
        if (sender instanceof Player) sender.showTitle(title);
    }

    private void kickPlayer(Optional<Player> player, @Nullable Component reason) {
        player.ifPresent(value -> value.disconnect(reason == null ? kickReason : reason));
    }

    @Override
    public void kickPlayer(@Nullable Component reason, @NotNull UUID target) {
        kickPlayer(VelocityApp.getInstance().getServer().getPlayer(target), reason);
    }

    @Override
    public void kickPlayer(@Nullable String reason, @NotNull UUID target) {
        kickPlayer(VelocityApp.getInstance().getServer().getPlayer(target), reason == null ? null : Component.text(reason));
    }

    @Override
    public void kickPlayer(@Nullable Component reason, @NotNull String target) {
        kickPlayer(VelocityApp.getInstance().getServer().getPlayer(target), reason);
    }

    @Override
    public void kickPlayer(@Nullable String reason, @NotNull String target) {
        kickPlayer(VelocityApp.getInstance().getServer().getPlayer(target), reason == null ? null : Component.text(reason));
    }
}
