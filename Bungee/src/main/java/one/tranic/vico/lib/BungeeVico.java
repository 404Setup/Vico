package one.tranic.vico.lib;

import one.tranic.vico.lib.message.MessageSenderImpl;
import one.tranic.vico.lib.player.PluginPlayerImpl;
import org.jetbrains.annotations.Nullable;

public class BungeeVico implements VicoImpl {
    @Override
    public MessageSenderImpl getMessageSender() {
        return null;
    }

    @Override
    public void close() {
        VicoImpl.super.close();
    }

    @Override
    public @Nullable PluginPlayerImpl getPlayer() {
        return VicoImpl.super.getPlayer();
    }
}
