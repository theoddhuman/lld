package com.subham.lld.taskscheduler.model;


import lombok.Getter;
import lombok.Setter;

/**
 * Author: the_odd_human
 * Date: 18/02/25
 */
public abstract class ScheduledTask {
    @Setter
    @Getter
    private long id;
    final ExecutionContext executionContext;

    public ScheduledTask(ExecutionContext executionContext) {
        this.executionContext = executionContext;
    }

    public void execute() {
        this.executionContext.execute(id);
    }

    public abstract boolean isRecurring();

    public abstract ScheduledTask getNextScheduledTask();

    public abstract long getNextExecutionTime();

}
