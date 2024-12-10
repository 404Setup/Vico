package one.tranic.vico.lib.scheduled;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.velocitypowered.api.proxy.ProxyServer;
import one.tranic.vico.lib.scheduled.task.VelocityScheduledTask;
import one.tranic.vico.lib.scheduled.task.TaskImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class VelocityScheduler<C> implements ProxySchedulerImpl<C> {
    private final ProxyServer server;
    private final Executor executor;

    public VelocityScheduler(ProxyServer server) {
        this.server = server;
        this.executor = Executors.newFixedThreadPool(3, new ThreadFactoryBuilder()
                        .setNameFormat("Vico Async Event Executor - #%d").setDaemon(true).build());
    }

    @Override
    public TaskImpl<C> runAsyncOnProxy(@NotNull C plugin, @NotNull Runnable task) {
        return new VelocityScheduledTask<>(server.getScheduler().buildTask(plugin, task).schedule());
    }

    @Override
    public @Nullable TaskImpl<C> runAsyncOnPlugin(@NotNull C plugin, @NotNull Runnable task) {
        this.executor.execute(task);
        return null;
    }

    @Override
    public void cancel(@NotNull C plugin) {
        throw new UnsupportedOperationException();
    }
}
