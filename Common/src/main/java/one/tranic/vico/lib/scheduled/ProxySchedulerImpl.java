package one.tranic.vico.lib.scheduled;

import one.tranic.vico.lib.scheduled.task.TaskImpl;
import org.jetbrains.annotations.NotNull;

public interface ProxySchedulerImpl<C> {
    TaskImpl<C> runSync(@NotNull C plugin, @NotNull Runnable task);

    TaskImpl<C> runAsync(@NotNull C plugin, @NotNull Runnable task);

    void cancel(@NotNull C plugin);
}
