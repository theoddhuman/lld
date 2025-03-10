package com.subham.lld.messageQueue;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Topic topic1 = new Topic("Topic1", 3);

        Publisher publisherA = new Publisher("A");
        Publisher publisherB = new Publisher("B");

        Subscriber subscriber1 = new Subscriber("1");
        Subscriber subscriber2 = new Subscriber("2");

        subscriber1.subscribe(topic1);
        subscriber2.subscribe(topic1);

        publisherA.publish(topic1, "Message 1 from Publisher A");
        publisherB.publish(topic1, "Message 2 from Publisher B");
        publisherA.publish(topic1, "Message 3 from Publisher A");
        publisherB.publish(topic1, "Message 4 from Publisher B");

        Thread.sleep(5000);
        Topic topic2 = new Topic("Topic2", 2);
        publisherA.publish(topic2, "Message 1 from Publisher A to topic2");
        publisherB.publish(topic2, "Message 2 from Publisher B to topic2");

        subscriber1.subscribe(topic2);
        Thread.sleep(5000);
        subscriber2.subscribe(topic2);

    }
}
