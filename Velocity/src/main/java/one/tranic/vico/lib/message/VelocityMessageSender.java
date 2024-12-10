package one.tranic.vico.lib.message;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class VelocityMessageSender implements MessageSenderImpl {
    @Override
    public void sendMessage(@Nullable Component message, @NotNull Object sender) {
        if (message == null) return;
        ((com.velocitypowered.api.command.CommandSource) sender).sendMessage(message);
    }
}
