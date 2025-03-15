package com.subham.lld.taskscheduler.service;


import java.util.ArrayList;
import java.util.List;

/**
 * Author: the_odd_human
 * Date: 18/02/25
 */
public class TaskScheduler {
    List<TaskRunner> taskRunners;

    List<Thread> threads;

    public TaskScheduler(int threadCount, TaskService taskService) {
        this.taskRunners = new ArrayList<>();
        this.threads = new ArrayList<>();
        for(int i=0; i<threadCount; i++) {
            TaskRunner taskRunner = new TaskRunner(taskService);
            taskRunners.add(taskRunner);
            threads.add(new Thread(taskRunner));
        }
    }

    public void start() {
        threads.forEach(Thread::start);
    }

    public void stop() {
        taskRunners.forEach(TaskRunner::stop);
        threads.forEach(thread -> {
            thread.interrupt();
            try{
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }
}
