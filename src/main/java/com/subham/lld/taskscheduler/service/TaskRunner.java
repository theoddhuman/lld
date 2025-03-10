package com.subham.lld.taskscheduler.service;


import com.subham.lld.taskscheduler.ScheduledTask;

/**
 * Author: the_odd_human
 * Date: 18/02/25
 */
public class TaskRunner implements Runnable {
    private final TaskService taskService;

    private volatile boolean running;

    public TaskRunner(TaskService taskService) {
        this.taskService = taskService;
        this.running = true;
    }

    @Override
    public void run() {
        while (running && !Thread.currentThread().isInterrupted()) {
            try {
                ScheduledTask task = taskService.getTaskForExecution();
                long delay = task.getNextExecutionTime() - System.currentTimeMillis();
                if (delay > 0) {
                    taskService.addTaskForExecution(task);
                    synchronized (this) {
                        wait(delay);
                    }
                } else {
                    task.execute();
                    if (task.isRecurring()) {
                        taskService.addTask(task.getNextScheduledTask());
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void stop() {
        this.running = false;
        synchronized (this) {
            notify();
        }
    }


}
