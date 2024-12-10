package one.tranic.vico.lib.scheduled;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import one.tranic.vico.lib.scheduled.task.BungeeScheduledTask;
import one.tranic.vico.lib.scheduled.task.TaskImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BungeeScheduler implements ProxySchedulerImpl<Plugin> {
    private final ProxyServer server;
    private final Executor executor;

    public BungeeScheduler(ProxyServer server) {
        this.server = server;
        this.executor = Executors.newFixedThreadPool(3, new ThreadFactoryBuilder()
                .setNameFormat("Vico Async Event Executor - #%d").setDaemon(true).build());
    }

    @Override
    public TaskImpl<Plugin> runAsyncOnProxy(@NotNull Plugin plugin, @NotNull Runnable task) {
        return new BungeeScheduledTask<>(server.getScheduler().runAsync(plugin, task));
    }

    @Override
    public @Nullable TaskImpl<Plugin> runAsyncOnPlugin(@NotNull Plugin plugin, @NotNull Runnable task) {
        return new BungeeScheduledTask<>(server.getScheduler().runAsync(plugin, task));
    }

    @Override
    public void cancel(@NotNull Plugin plugin) {
        server.getScheduler().cancel(plugin);
    }
}
