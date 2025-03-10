package com.subham.lld.taskscheduler.repository;


import com.subham.lld.taskscheduler.ScheduledTask;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Author: the_odd_human
 * Date: 18/02/25
 */
public class TaskRepository {
    private static Map<Long, ScheduledTask> tasks;
    private static PriorityQueue<Long> taskQueue;
    private static final AtomicLong ID = new AtomicLong(0);

    public TaskRepository() {
        tasks = new LinkedHashMap<>();
        taskQueue = new PriorityQueue<>(Comparator.comparingLong(id -> tasks.get(id).getNextExecutionTime()));
    }

    public void addTask(ScheduledTask task) {
        long id = ID.getAndIncrement();
        task.setId(id);
        tasks.put(id, task);
        taskQueue.add(id);
    }

    public boolean removeTask(long id) {
        if(tasks.containsKey(id)) {
            taskQueue.remove(id);
            tasks.remove(id);
            return true;
        }
        return false;
    }

    public void addToQueue(ScheduledTask task) {
        taskQueue.add(task.getId());
    }

    public ScheduledTask removeFromQueue() {
        if(taskQueue.isEmpty()) {
            return null;
        }
        long id = taskQueue.remove();
        return tasks.get(id);
    }

    public ScheduledTask top() {
        if(taskQueue.isEmpty()) {
            return null;
        }
        long id = taskQueue.peek();
        return tasks.get(id);
    }
}
