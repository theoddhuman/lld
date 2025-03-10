package com.subham.lld.taskscheduler.service.impl;


import com.subham.lld.taskscheduler.ScheduledTask;
import com.subham.lld.taskscheduler.repository.TaskRepository;
import com.subham.lld.taskscheduler.service.TaskService;

/**
 * Author: the_odd_human
 * Date: 18/02/25
 */
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void addTask(ScheduledTask task) {
        this.taskRepository.addTask(task);
    }

    @Override
    public boolean getTaskForExecution(long id) {
        return this.taskRepository.removeTask(id);
    }

    @Override
    public ScheduledTask getTaskForExecution() {
        return this.taskRepository.removeFromQueue();
    }

    @Override
    public void addTaskForExecution(ScheduledTask task) {
        this.taskRepository.addToQueue(task);
    }

    @Override
    public ScheduledTask topTask() {
        return this.taskRepository.top();
    }
}
