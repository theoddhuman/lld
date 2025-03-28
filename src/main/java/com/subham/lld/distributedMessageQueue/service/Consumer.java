package com.subham.lld.distributedMessageQueue.service;


import com.subham.lld.distributedMessageQueue.model.Message;
import com.subham.lld.distributedMessageQueue.queue.Broker;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: the_odd_human
 * Date: 17/03/25
 */
public class Consumer<T> implements Runnable{
    @Getter
    private String name;

    @Getter
    private String groupName;

    private Broker<T> broker;

    @Getter
    private String topic;

    private List<Integer> partitions;

    private Thread thread;

    public Consumer(String name, String groupName, Broker<T> broker, String topic) {
        this.name = name;
        this.groupName = groupName;
        this.broker = broker;
        this.topic = topic;
        this.partitions = new ArrayList<>();
        this.thread = new Thread(this);
        this.thread.start();
    }

    @Override
    public void run() {
        subscribe(topic);
        while(true) {
            for (Integer partition : partitions) {
                this.receive(partition);
            }
        }
    }

    private void subscribe(String topic) {
        this.topic = topic;
        this.broker.subscribe(this);
    }

    public void receive(int partition) {
        Message<T> message = broker.getMessage(this, partition);
        if(message == null) {
            return;
        }
        System.out.println(name +": "+message);
        broker.acknowledge(groupName, topic, partition, message.getOffset());
    }

    public void assignPartitions(List<Integer> partitions) {
        System.out.println("Consumer: " + name + " got assigned partitions: " + partitions);
        this.partitions = partitions;
    }
}
