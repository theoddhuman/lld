package com.subham.lld.taskscheduler.model;


/**
 * Author: the_odd_human
 * Date: 18/02/25
 */
public class RecurringTask extends ScheduledTask {
    private final long executionTime;
    private final long interval;

    public RecurringTask(ExecutionContext executionContext, long executionTime, long interval) {
        super(executionContext);
        this.executionTime = executionTime;
        this.interval = interval;
    }

    @Override
    public boolean isRecurring() {
        return true;
    }

    @Override
    public ScheduledTask getNextScheduledTask() {
        return new RecurringTask(executionContext, executionTime + interval, interval);
    }

    @Override
    public long getNextExecutionTime() {
        return this.executionTime;
    }
}
