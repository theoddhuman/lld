package com.subham.lld.messageQueue;

public class Publisher {
    private String name;

    public Publisher(String name) {
        this.name = name;
    }


    public void publish(Topic topic, String content) {
        Partition partition = topic.getPartition();
        Message message = new Message();
        message.setContent(content);
        partition.addMessage(message);
        System.out.println("Publisher "+name+ " published to "+topic.getName()+ " " +partition.getId());
    }
}
