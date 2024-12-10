package one.tranic.vico.lib;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Plugin;
import one.tranic.vico.lib.message.BungeeMessageSender;
import one.tranic.vico.lib.message.MessageSenderImpl;

public class BungeeVico implements VicoImpl {
    private final MessageSenderImpl<CommandSender, Plugin> bms;

    public BungeeVico() {
        this.bms = new BungeeMessageSender();
        this.bms.adventure();
    }

    @Override
    public MessageSenderImpl<CommandSender, Plugin> getMessageSender() {
        return bms;
    }

    @Override
    public void close() {
        bms.close();
    }
}
