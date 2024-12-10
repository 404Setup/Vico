package one.tranic.vico.lib.scheduled.task;

import net.md_5.bungee.api.scheduler.ScheduledTask;

public class BungeeScheduledTask<C> implements TaskImpl<C> {
    private final ScheduledTask bcTask;

    public BungeeScheduledTask(ScheduledTask bcTask) {
        this.bcTask = bcTask;
    }

    @Override
    public void cancel() {
        bcTask.cancel();
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public C getOwner() {
        return (C) bcTask.getOwner();
    }

    @Override
    public boolean isRepeatingTask() {
        return false;
    }

    @Override
    public boolean isSynchronized() {
        return false;
    }
}
