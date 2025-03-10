package com.subham.lld.taskscheduler;


import com.subham.lld.taskscheduler.repository.TaskRepository;
import com.subham.lld.taskscheduler.service.TaskScheduler;
import com.subham.lld.taskscheduler.service.TaskService;
import com.subham.lld.taskscheduler.service.impl.TaskServiceImpl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

/**
 * Author: the_odd_human
 * Date: 18/02/25
 */
public class Test {
    public static void main(String[] args) {
        TaskRepository taskRepository = new TaskRepository();
        TaskService taskService = new TaskServiceImpl(taskRepository);
        taskService.addTask(new OneTimeTask(new ExecutionContextImpl(), LocalDateTime.now().plusSeconds(1).atZone(ZoneId.systemDefault()).toEpochSecond()));
        taskService.addTask(new OneTimeTask(new ExecutionContextImpl(), LocalDateTime.now().plusSeconds(5).toInstant(ZoneOffset.UTC).toEpochMilli()));
        taskService.addTask(new RecurringTask(new ExecutionContextImpl(), LocalDateTime.now().plusSeconds(1).toInstant(ZoneOffset.UTC).toEpochMilli(), 5000));

        TaskScheduler taskScheduler = new TaskScheduler(1, taskService);
    }
}
