package one.tranic.vico.lib.scheduled;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import one.tranic.vico.lib.scheduled.task.BungeeScheduledTask;
import one.tranic.vico.lib.scheduled.task.TaskImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.TimeUnit;

public class BungeeScheduler implements ProxySchedulerImpl<Plugin> {
    private final ProxyServer server;

    public BungeeScheduler(ProxyServer server) {
        this.server = server;
    }

    /**
     * Returns a task that will run on the next server tick.
     *
     * @param plugin the reference to the plugin scheduling task
     * @param task   the task to be run
     * @throws IllegalArgumentException if plugin is null
     * @throws IllegalArgumentException if task is null
     */
    @Override
    public TaskImpl<Plugin> runSync(@NotNull Plugin plugin, @NotNull Runnable task) {
        return new BungeeScheduledTask<>(server.getScheduler().schedule(plugin, task, 1, TimeUnit.MILLISECONDS));
    }

    @Override
    public @Nullable TaskImpl<Plugin> runAsync(@NotNull Plugin plugin, @NotNull Runnable task) {
        return new BungeeScheduledTask<>(server.getScheduler().runAsync(plugin, task));
    }

    @Override
    public void cancel(@NotNull Plugin plugin) {
        server.getScheduler().cancel(plugin);
    }
}
