package com.subham.lld.taskscheduler.model;


/**
 * Author: the_odd_human
 * Date: 18/02/25
 */
public class ExecutionContext {
    public void execute(long taskId) {
        System.out.println(taskId + ": executed at " + System.currentTimeMillis());
    }
}
