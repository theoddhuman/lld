package com.subham.lld.taskscheduler.model;


/**
 * Author: the_odd_human
 * Date: 18/02/25
 */
public class OneTimeTask extends ScheduledTask {
    private final long executionTime;

    public OneTimeTask(ExecutionContext executionContext, long executionTime) {
        super(executionContext);
        this.executionTime = executionTime;
    }

    @Override
    public boolean isRecurring() {
        return false;
    }

    @Override
    public ScheduledTask getNextScheduledTask() {
        return null;
    }

    @Override
    public long getNextExecutionTime() {
        return this.executionTime;
    }
}
