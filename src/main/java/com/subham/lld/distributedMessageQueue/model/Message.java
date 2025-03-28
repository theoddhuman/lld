package com.subham.lld.distributedMessageQueue.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Message<T> {
    private long messageId;

    private T content;

    private String topic;

    private int partition;

    private int offset;
}
