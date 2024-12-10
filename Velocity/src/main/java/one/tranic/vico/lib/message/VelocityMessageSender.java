package one.tranic.vico.lib.message;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
}
