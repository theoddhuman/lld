package com.subham.lld.taskscheduler.service;

import com.subham.lld.taskscheduler.ScheduledTask;

public interface TaskService {
    void addTask(ScheduledTask task);

    boolean getTaskForExecution(long id);

    ScheduledTask getTaskForExecution();

    void addTaskForExecution(ScheduledTask task);

    ScheduledTask topTask();
}
