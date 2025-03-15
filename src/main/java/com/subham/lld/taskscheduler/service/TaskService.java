package com.subham.lld.taskscheduler.service;

import com.subham.lld.taskscheduler.model.ScheduledTask;

public interface TaskService {
    void addTask(ScheduledTask task);

    ScheduledTask getTaskForExecution();

    void addTaskForExecution(ScheduledTask task);
}
