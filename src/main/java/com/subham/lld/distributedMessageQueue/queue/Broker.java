package com.subham.lld.distributedMessageQueue.queue;


import com.subham.lld.distributedMessageQueue.service.Consumer;
import com.subham.lld.distributedMessageQueue.model.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Author: the_odd_human
 * Date: 17/03/25
 */
public class Broker<T> {

    private final ServiceRegistry serviceRegistry;
    private final AtomicLong messageId;
    private Map<String, Map<Integer, List<Message<T>>>> messages;

    public Broker(ServiceRegistry serviceRegistry) {
        this.serviceRegistry = serviceRegistry;
        this.messages = new HashMap<>();
        this.messageId = new AtomicLong(0);
    }

    public boolean createTopic(String topic, int partitionCount) {
        if(!serviceRegistry.registerTopic(topic, partitionCount)) {
            return false;
        }
        this.messages.put(topic, IntStream.range(0, partitionCount)
                .boxed().collect(Collectors.toMap(partition -> partition, partition -> new ArrayList<>())));
        System.out.println("Topic created: " + topic);
        return true;
    }

    public boolean publish(Message<T> message) {
        long msgId = messageId.getAndIncrement();
        message.setMessageId(messageId.get());
        Map<Integer, List<Message<T>>> partitions = messages.get(message.getTopic());
        int partition = (int) (msgId % partitions.size());
        List<Message<T>> partMessages = partitions.get(partition);
        synchronized (partMessages) {
            message.setPartition(partition);
            message.setOffset(partMessages.size());
            partMessages.add(message);
        }
        return true;
    }

    public void subscribe(Consumer<T> consumer) {
        this.serviceRegistry.subscribe(consumer);
    }

    public void acknowledge(String consumerGroup, String topic, int partition, int offset) {
        this.serviceRegistry.acknowledge(consumerGroup, topic, partition, offset);
    }

    public Message<T> getMessage(Consumer<T> consumer, int partition) {
        int offset = this.serviceRegistry.getOffset(consumer, partition);
        List<Message<T>> messageList = this.messages.get(consumer.getTopic()).get(partition);
        if(messageList.size() <= offset) {
            return null;
        }
        return messageList.get(offset);
    }
}
