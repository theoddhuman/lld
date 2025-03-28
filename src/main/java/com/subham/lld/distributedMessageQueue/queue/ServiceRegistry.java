package com.subham.lld.distributedMessageQueue.queue;


import com.subham.lld.distributedMessageQueue.service.Consumer;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Author: the_odd_human
 * Date: 17/03/25
 *
 * This class
 * Stores the meta-data of topic and consumer
 * Stores consumer group states like offset/partition
 *
 */
public class ServiceRegistry {
    //<topic>/[<partition>]
    private Map<String, List<Integer>> topics;

    //<consumer-group>/<topic>/<consumer>/[<partition>]
    private Map<String, Map<String, List<Consumer<?>>>> consumerPartitions;

    //<consumer-group>/<topic>/<partition>/<offset>
    private Map<String, Map<String, Map<Integer, Integer>>> offsets;

    public ServiceRegistry() {
        this.topics = new HashMap<>();
        this.consumerPartitions = new HashMap<>();
        this.offsets = new HashMap<>();
    }

    public boolean registerTopic(String topic, int partitionCount) {
        if (topics.containsKey(topic)) {
            return false;
        }
        //creating partitions
        List<Integer> partitions = IntStream.range(0, partitionCount).boxed().collect(Collectors.toList());
        topics.put(topic, partitions);
        return true;
    }

    public void subscribe(Consumer<?> consumer) {
        String consumerGroup = consumer.getGroupName();
        String topic = consumer.getTopic();
        consumerPartitions.putIfAbsent(consumerGroup, new HashMap<>());
        offsets.putIfAbsent(consumerGroup, new HashMap<>());
        offsets.get(consumerGroup).putIfAbsent(topic, topics.get(topic).stream()
                .collect(Collectors.toMap(partition -> partition, partition -> 0)));
        consumerPartitions.get(consumerGroup).putIfAbsent(topic, new ArrayList<>());
        List<Consumer<?>> consumers = consumerPartitions.get(consumerGroup).get(topic);
        consumers.add(consumer);

        //assigning partitions to consumers
        int partitionCount = topics.get(topic).size();
        int consumerCount = consumers.size();
        int partitionPerConsumer = partitionCount / consumerCount;
        int consumerWithExtraPartition = partitionCount % consumerCount;
        int consumerIndex = 0;
        for (int i = 0; i < partitionCount && consumerIndex < consumerCount; ) {
            List<Integer> assignedPartitions = new ArrayList<>();
            int partitionToBeAssigned = partitionPerConsumer;
            if (consumerIndex < consumerWithExtraPartition) {
                partitionToBeAssigned = partitionPerConsumer + 1;
            }
            for (int j = i; j < i + partitionToBeAssigned; j++) {
                assignedPartitions.add(j);
            }
            i += partitionToBeAssigned;
            consumers.get(consumerIndex).assignPartitions(assignedPartitions);
            consumerIndex++;
        }
    }

    public int getOffset(Consumer<?> consumer, int partition) {
        return this.offsets.get(consumer.getGroupName()).get(consumer.getTopic()).get(partition);
    }

    public void acknowledge(String consumerGroup, String topic, int partition, int offset) {
        Map<Integer, Integer> partitionMap = this.offsets.get(consumerGroup).get(topic);
        partitionMap.put(partition, offset+1);
    }
}
