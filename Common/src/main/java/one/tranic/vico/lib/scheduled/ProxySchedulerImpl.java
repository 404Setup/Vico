package one.tranic.vico.lib.scheduled;

import one.tranic.vico.lib.scheduled.task.TaskImpl;
import org.jetbrains.annotations.NotNull;

public interface ProxySchedulerImpl<C> {
    /***
     * Executes asynchronous tasks on a proxy server.
     *
     * @param plugin the plugin owning this task – the task to run
     * @param task the task to be run
     * @return {@link TaskImpl<C>}
     */
    TaskImpl<C> runAsyncOnProxy(@NotNull C plugin, @NotNull Runnable task);

    /***
     * Perform asynchronous tasks on plugins.
     *
     * @param plugin the plugin owning this task – the task to run
     * @param task the task to be run
     * @return {@link TaskImpl<C>}
     */
    TaskImpl<C> runAsyncOnPlugin(@NotNull C plugin, @NotNull Runnable task);

    /**
     * Cancel all tasks owned by this plugin, this preventing them from being executed hereon in.
     *
     * It is generally recommended to use {@link TaskImpl<C>#cancel()}
     *
     * @param plugin the plugin owning the tasks to be canceled
     */
    void cancel(@NotNull C plugin);
}
