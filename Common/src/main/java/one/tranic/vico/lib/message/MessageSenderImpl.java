package one.tranic.vico.lib.message;

import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.platform.AudienceProvider;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.title.Title;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public interface MessageSenderImpl<C, B> {
    Component kickReason = Component.text("Being kicked from the server");
    String kickReasonStr = "Being kicked from the server";

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

    void kickPlayer(@Nullable Component reason, @NotNull UUID target);

    void kickPlayer(@Nullable String reason, @NotNull UUID target);

    void kickPlayer(@Nullable Component reason, @NotNull String target);

    void kickPlayer(@Nullable String reason, @NotNull String target);

    default void close() {
        throw new UnsupportedOperationException();
    }
}
