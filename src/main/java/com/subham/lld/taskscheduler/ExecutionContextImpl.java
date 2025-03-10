package com.subham.lld.taskscheduler;


/**
 * Author: the_odd_human
 * Date: 18/02/25
 */
public class ExecutionContextImpl implements ExecutionContext {
    @Override
    public void execute() {
        System.out.println("executed at " + System.currentTimeMillis());
    }
}
