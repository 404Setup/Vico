package one.tranic.vico.lib;

import one.tranic.vico.Platform;
import one.tranic.vico.lib.scheduled.BungeeScheduler;
import one.tranic.vico.lib.scheduled.ProxySchedulerImpl;
import one.tranic.vico.lib.scheduled.VelocityScheduler;
import org.jetbrains.annotations.NotNull;

public class VicoAPI {
    public <T> ProxySchedulerImpl getProxyScheduler(T proxyServer) {
        @NotNull Platform platform = Platform.get();
        if (platform == Platform.Velocity) {
            return new VelocityScheduler((com.velocitypowered.api.proxy.ProxyServer) proxyServer);
        } else if (platform == Platform.BungeeCord) {
            return new BungeeScheduler((net.md_5.bungee.api.ProxyServer) proxyServer);
        }
        throw new IllegalArgumentException("Unsupported platform: " + platform);
    }

    public VicoImpl getVicoPlugin() {
        @NotNull Platform platform = Platform.get();
        if (platform == Platform.Spigot) return new SpigotVico();
        if (platform == Platform.Paper || platform == Platform.Folia || platform == Platform.ShreddedPaper)
            return new PaperVico();
        throw new IllegalArgumentException("Unsupported platform: " + platform);
    }
}
