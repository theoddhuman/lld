package com.subham.lld.messageQueue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Partition {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    private final List<Message> messageList;

    private final Map<Subscriber, AtomicInteger> subscriberOffsets;

    public Partition(int id) {
        this.id = id;
        messageList = Collections.synchronizedList(new ArrayList<>());
        subscriberOffsets = new ConcurrentHashMap<>();
    }

    public synchronized void addMessage(Message message) {
        messageList.add(message);
    }

    public void registerSubscriber(Subscriber subscriber) {
        subscriberOffsets.putIfAbsent(subscriber, new AtomicInteger(0));
    }

    public Message getNextMessage(Subscriber subscriber) {
        AtomicInteger subscriberOffset = subscriberOffsets.get(subscriber);
        int current = subscriberOffset.get();
        if(current < messageList.size()) {
            subscriberOffset.incrementAndGet();
            return messageList.get(current);
        }
        return null;
    }
}
