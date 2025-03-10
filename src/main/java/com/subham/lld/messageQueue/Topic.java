package com.subham.lld.messageQueue;

import jakarta.servlet.http.Part;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class Topic {
    private String name;

    public AtomicInteger getPartitionIndex() {
        return partitionIndex;
    }

    public void setPartitionIndex(AtomicInteger partitionIndex) {
        this.partitionIndex = partitionIndex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Partition> getPartitions() {
        return partitions;
    }

    public void setPartitions(List<Partition> partitions) {
        this.partitions = partitions;
    }

    private AtomicInteger partitionIndex;
    private List<Partition> partitions;

    public Topic(String name, int partitionCount) {
        this.name = name;
        partitions = new ArrayList<>();
        for(int i=0; i<partitionCount; i++) {
            partitions.add(new Partition(i));
        }
        partitionIndex = new AtomicInteger();
        partitionIndex.set(0);
    }

    public Partition getPartition() {
        int partIndex = partitionIndex.getAndIncrement();
        return partitions.get(partIndex%partitions.size());
    }

    public Partition getPartition(int i) {
        return partitions.get(i);
    }
}
