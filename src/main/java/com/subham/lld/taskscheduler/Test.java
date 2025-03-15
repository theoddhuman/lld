package com.subham.lld.taskscheduler;


import com.subham.lld.taskscheduler.model.ExecutionContext;
import com.subham.lld.taskscheduler.model.OneTimeTask;
import com.subham.lld.taskscheduler.model.RecurringTask;
import com.subham.lld.taskscheduler.service.TaskScheduler;
import com.subham.lld.taskscheduler.service.TaskService;
import com.subham.lld.taskscheduler.service.impl.TaskServiceImpl;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Author: the_odd_human
 * Date: 18/02/25
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        TaskService taskService = TaskServiceImpl.getInstance();
        taskService.addTask(new OneTimeTask(new ExecutionContext(), LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()));
        taskService.addTask(new OneTimeTask(new ExecutionContext(), LocalDateTime.now().plusSeconds(5).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()));
        taskService.addTask(new RecurringTask(new ExecutionContext(), LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(), 6000));

        TaskScheduler taskScheduler = new TaskScheduler(1, taskService);
        taskScheduler.start();
        Thread.sleep(10000);
        taskScheduler.stop();
    }
}
