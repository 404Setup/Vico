package one.tranic.vico.lib;

import one.tranic.vico.lib.message.MessageSenderImpl;
import one.tranic.vico.lib.player.PluginPlayerImpl;
import org.jetbrains.annotations.Nullable;

public interface VicoImpl {
    MessageSenderImpl<?, ?> getMessageSender();

    default void close() {
    }

    default @Nullable PluginPlayerImpl getPluginPlayer() {
        return null;
    }
}
