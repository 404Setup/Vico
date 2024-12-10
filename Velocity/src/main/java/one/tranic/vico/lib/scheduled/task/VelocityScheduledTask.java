package one.tranic.vico.lib.scheduled.task;

import com.velocitypowered.api.scheduler.ScheduledTask;

public class VelocityScheduledTask<C> implements TaskImpl<C> {
    private final ScheduledTask vcTask;

    public VelocityScheduledTask(ScheduledTask vcTask) {
        this.vcTask = vcTask;
    }

    @Override
    public void cancel() {
        vcTask.cancel();
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public C getOwner() {
        return (C) vcTask.plugin();
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
