package one.tranic.vico.lib.message;

import net.kyori.adventure.platform.AudienceProvider;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface MessageSenderImpl<C, B> {
    default @Nullable AudienceProvider adventure() {
        throw new UnsupportedOperationException();
    }

    default void setPlugin(@NotNull B plugin) {
        throw new UnsupportedOperationException();
    }

    default void sendMessage(@Nullable String message, @NotNull C sender) {
        if (message == null) return;
        sendMessage(Component.text(message), sender);
    }

    void sendMessage(@Nullable Component message, @NotNull C sender);

    default void sendMessage(@Nullable ComponentLike message, C sender) {
        if (message == null) return;
        sendMessage(message.asComponent(), sender);
    }

    default void close() {
        throw new UnsupportedOperationException();
    }
}
