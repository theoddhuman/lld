package com.subham.lld.distributedMessageQueue;


import com.subham.lld.distributedMessageQueue.queue.Broker;
import com.subham.lld.distributedMessageQueue.queue.ServiceRegistry;
import com.subham.lld.distributedMessageQueue.service.Consumer;
import com.subham.lld.distributedMessageQueue.service.Producer;

/**
 * Author: the_odd_human
 * Date: 17/03/25
 */
public class Client {
    public static void main(String[] args) throws InterruptedException {
        ServiceRegistry serviceRegistry = new ServiceRegistry();
        Broker<String> broker = new Broker<>(serviceRegistry);

        broker.createTopic("test_event", 2);

        Consumer<String> consumer1 = new Consumer<>("consumer-1", "my-group", broker, "test_event");
        Thread.sleep(1000);
        Consumer<String> consumer2 = new Consumer<>("consumer-2", "my-group", broker, "test_event");
        Thread.sleep(1000);
        Consumer<String> consumer3 = new Consumer<>("consumer-3", "my-group", broker, "test_event");

        Producer<String> producer = new Producer<>("myProd", broker);
        new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                producer.publish("test_event", "My-message-thread-1 - " + i);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                producer.publish("test_event", "My-message-thread-2 - " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
