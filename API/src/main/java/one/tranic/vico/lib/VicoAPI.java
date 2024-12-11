package one.tranic.vico.lib;

import one.tranic.vico.utils.Platform;
import one.tranic.vico.lib.scheduled.BungeeScheduler;
import one.tranic.vico.lib.scheduled.PluginSchedulerBuilder;
import one.tranic.vico.lib.scheduled.ProxySchedulerImpl;
import one.tranic.vico.lib.scheduled.VelocityScheduler;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class VicoAPI {
    /***
     * Get the proxy server scheduler.
     * <p>
     * If you need a plugin server scheduler, use {@link PluginSchedulerBuilder#builder(org.bukkit.plugin.Plugin)}.
     * @param proxyServer
     * @return {@link ProxySchedulerImpl}
     */
    public static ProxySchedulerImpl getProxyScheduler(Object proxyServer) {
        VicoImpl plugin = VicoAPI.getVicoPlugin();
        plugin.getPluginPlayer().teleport(player, location);
        CompletableFuture<Boolean> result = plugin.getPluginPlayer().teleportAsync(player, location);
        plugin.getMessageSender().close(); // Called when the plugin is closed
        @NotNull Platform platform = Platform.get();
        if (platform == Platform.Velocity) {
            return new VelocityScheduler((com.velocitypowered.api.proxy.ProxyServer) proxyServer);
        } else if (platform == Platform.BungeeCord) {
            return new BungeeScheduler((net.md_5.bungee.api.ProxyServer) proxyServer);
        }
        throw new IllegalArgumentException("Unsupported platform: " + platform);
    }

    public static VicoImpl getVicoPlugin() {
        @NotNull Platform platform = Platform.get();
        return switch (platform) {
            case Spigot -> new SpigotVico();
            case Folia, ShreddedPaper, Paper -> new PaperVico();
            case BungeeCord -> new BungeeVico();
            case Velocity -> new VelocityVico();
            default -> throw new IllegalArgumentException("Unsupported platform: " + platform);
        };
    }
}
