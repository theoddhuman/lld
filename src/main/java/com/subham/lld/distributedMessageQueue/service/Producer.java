package com.subham.lld.distributedMessageQueue.service;


import com.subham.lld.distributedMessageQueue.model.Message;
import com.subham.lld.distributedMessageQueue.queue.Broker;
import lombok.Getter;

/**
 * Author: the_odd_human
 * Date: 17/03/25
 */
public class Producer<T> {
    @Getter
    private String name;

    private Broker<T> broker;

    public Producer(String name, Broker<T> broker) {
        this.name = name;
        this.broker = broker;
    }

    public boolean publish(String topic, T message) {
        Message<T> finalMessage = Message.<T>builder()
                .content(message)
                .topic(topic)
                .build();
        System.out.println("Published message: " + message);
        return this.broker.publish(finalMessage);
    }
}
