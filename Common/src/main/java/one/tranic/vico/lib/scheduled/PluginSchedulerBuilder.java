package one.tranic.vico.lib.scheduled;

import io.papermc.paper.threadedregions.scheduler.AsyncScheduler;
import io.papermc.paper.threadedregions.scheduler.EntityScheduler;
import io.papermc.paper.threadedregions.scheduler.GlobalRegionScheduler;
import io.papermc.paper.threadedregions.scheduler.RegionScheduler;
import one.tranic.vico.utils.Platform;
import one.tranic.vico.lib.scheduled.task.FoliaScheduledTask;
import one.tranic.vico.lib.scheduled.task.SpigotScheduledTask;
import one.tranic.vico.lib.scheduled.task.TaskImpl;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class PluginSchedulerBuilder {
    private final Plugin plugin;
    private boolean folia = false;
    private boolean sync;
    private long delayTicks;
    private long period;
    private Runnable task;
    private Location location;
    private Entity entity;

    public PluginSchedulerBuilder(Plugin plugin) {
        this.folia = (Platform.get() == Platform.Folia) || (Platform.get() == Platform.ShreddedPaper);
        this.plugin = plugin;
    }

    public static PluginSchedulerBuilder builder(Plugin plugin) {
        return new PluginSchedulerBuilder(plugin);
    }

    public PluginSchedulerBuilder sync() {
        if (!this.sync) this.sync = true;
        return this;
    }

    public PluginSchedulerBuilder sync(@NotNull Location location) {
        if (this.location == null) {
            this.sync = true;
            this.location = location;
        }
        return this;
    }

    public PluginSchedulerBuilder sync(@NotNull Entity entity) {
        if (this.entity == null) {
            this.sync = true;
            this.entity = entity;
        }
        return this;
    }

    public PluginSchedulerBuilder async() {
        if (this.sync && (!folia && this.location == null && this.entity == null)) this.sync = false;
        return this;
    }

    public PluginSchedulerBuilder setTask(@NotNull Runnable runnable) {
        if (this.task == null) this.task = runnable;
        return this;
    }

    public PluginSchedulerBuilder setDelayTicks(long delayTicks) {
        if (this.folia && delayTicks < 1L) this.delayTicks = 1L;
        else this.delayTicks = delayTicks;
        return this;
    }

    public PluginSchedulerBuilder setPeriod(long period) {
        if (this.folia && period < 1L) this.period = 1L;
        else this.period = period;
        return this;
    }

    public TaskImpl<Plugin> run() {
        if (this.task == null) throw new UnsupportedOperationException("It seems that the task has not been set.");
        if (folia) return runFoliaTask();
        return runBukkitTask();
    }

    private TaskImpl<Plugin> runFoliaTask() {
        if (sync) {
            if (this.entity != null) return newFoliaEntityTask();
            if (this.location != null) return newFoliaRegionTask();
            return newFoliaGlobalRegionTask();
        }
        return newFoliaAsyncTask();
    }

    private TaskImpl<Plugin> runBukkitTask() {
        if (sync) return newBukkitSyncTask();
        return newBukkitAsyncTask();
    }

    private TaskImpl<Plugin> newFoliaEntityTask() {
        @NotNull EntityScheduler scheduler = this.entity.getScheduler();
        if (this.delayTicks != 0L) {
            if (this.period != 0L)
                return new FoliaScheduledTask(scheduler.runAtFixedRate(plugin, (e) -> task.run(), null, delayTicks, period));
            else return new FoliaScheduledTask(scheduler.runDelayed(plugin, (e) -> task.run(), null, delayTicks));
        }
        return new FoliaScheduledTask(scheduler.run(plugin, (e) -> task.run(), null));
    }

    private TaskImpl<Plugin> newFoliaRegionTask() {
        @NotNull RegionScheduler scheduler = Bukkit.getRegionScheduler();
        if (this.delayTicks != 0L) {
            if (this.period != 0L)
                return new FoliaScheduledTask(scheduler.runAtFixedRate(plugin, location, (e) -> task.run(), delayTicks, period));
            else
                return new FoliaScheduledTask(scheduler.runDelayed(plugin, location, (e) -> task.run(), delayTicks));
        }
        return new FoliaScheduledTask(scheduler.run(plugin, location, (e) -> task.run()));
    }

    private TaskImpl<Plugin> newFoliaGlobalRegionTask() {
        @NotNull GlobalRegionScheduler scheduler = Bukkit.getGlobalRegionScheduler();
        if (this.delayTicks != 0L) {
            if (this.period != 0L)
                return new FoliaScheduledTask(scheduler.runAtFixedRate(plugin, (e) -> task.run(), delayTicks, period));
            else return new FoliaScheduledTask(scheduler.runDelayed(plugin, (e) -> task.run(), delayTicks));
        }
        return new FoliaScheduledTask(scheduler.run(plugin, (e) -> task.run()));
    }

    private TaskImpl<Plugin> newFoliaAsyncTask() {
        @NotNull AsyncScheduler scheduler = Bukkit.getAsyncScheduler();
        if (this.delayTicks != 0L) {
            if (this.period != 0L)
                return new FoliaScheduledTask(scheduler.runAtFixedRate(plugin, (e) -> task.run(), delayTicks * 50, period * 50, TimeUnit.MILLISECONDS));
            else
                return new FoliaScheduledTask(scheduler.runDelayed(plugin, (e) -> task.run(), delayTicks * 50, TimeUnit.MILLISECONDS));
        }
        return new FoliaScheduledTask(scheduler.runNow(plugin, (e) -> task.run()));
    }

    private TaskImpl<Plugin> newBukkitSyncTask() {
        @NotNull BukkitScheduler scheduler = Bukkit.getScheduler();
        if (this.delayTicks != 0L) {
            if (this.period != 0L)
                return new SpigotScheduledTask(scheduler.runTaskTimer(plugin, task, delayTicks, period));
            else
                return new SpigotScheduledTask(scheduler.runTaskLater(plugin, task, delayTicks));
        }
        return new SpigotScheduledTask(scheduler.runTask(plugin, task));
    }

    private TaskImpl<Plugin> newBukkitAsyncTask() {
        @NotNull BukkitScheduler scheduler = Bukkit.getScheduler();
        if (this.delayTicks != 0L) {
            if (this.period != 0L)
                return new SpigotScheduledTask(scheduler.runTaskTimerAsynchronously(plugin, task, delayTicks, period));
            else
                return new SpigotScheduledTask(scheduler.runTaskLaterAsynchronously(plugin, task, delayTicks));
        }
        return new SpigotScheduledTask(scheduler.runTaskAsynchronously(plugin, task));
    }
}
