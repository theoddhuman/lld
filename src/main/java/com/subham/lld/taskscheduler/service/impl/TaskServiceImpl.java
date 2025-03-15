package com.subham.lld.taskscheduler.service.impl;


import com.subham.lld.taskscheduler.model.ScheduledTask;
import com.subham.lld.taskscheduler.repository.TaskRepository;
import com.subham.lld.taskscheduler.service.TaskService;

/**
 * Author: the_odd_human
 * Date: 18/02/25
 */
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    private TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    private static final class InstanceHolder {
        private static final TaskServiceImpl INSTANCE = new TaskServiceImpl(TaskRepository.getInstance());
    }

    public static TaskServiceImpl getInstance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public void addTask(ScheduledTask task) {
        this.taskRepository.addTask(task);
    }

    @Override
    public ScheduledTask getTaskForExecution() {
        return this.taskRepository.removeFromQueue();
    }

    @Override
    public void addTaskForExecution(ScheduledTask task) {
        this.taskRepository.addToQueue(task);
    }
}
