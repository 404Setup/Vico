package one.tranic.vico.lib.message;

import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.platform.AudienceProvider;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.title.Title;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface MessageSenderImpl<C, B> {
    default @Nullable AudienceProvider adventure() {
        throw new UnsupportedOperationException();
    }

    default void setPlugin(@NotNull B plugin) {
        throw new UnsupportedOperationException();
    }

    void sendMessage(@Nullable String message, @NotNull C sender);

    void sendMessage(@Nullable Component message, @NotNull C sender);

    default void sendMessage(@Nullable ComponentLike message, C sender) {
        if (message == null) return;
        sendMessage(message.asComponent(), sender);
    }

    void showBossbar(@NotNull BossBar bossBar, @NotNull C sender);

    void showTitle(@NotNull Title title, @NotNull C sender);

    default void close() {
        throw new UnsupportedOperationException();
    }
}
