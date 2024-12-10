package one.tranic.vico.lib;

import com.velocitypowered.api.command.CommandSource;
import one.tranic.vico.lib.message.MessageSenderImpl;
import one.tranic.vico.lib.message.VelocityMessageSender;

public class VelocityVico implements VicoImpl {
    private final MessageSenderImpl<CommandSource, Object> messageSender;

    public VelocityVico() {
        messageSender = new VelocityMessageSender();
    }

    @Override
    public MessageSenderImpl<CommandSource, Object> getMessageSender() {
        return messageSender;
    }
}
