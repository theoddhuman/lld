package com.subham.lld.concurrency.produceconsumer.semaphore;


/**
 * Author: the_odd_human
 * Date: 22/02/25
 */
public class Test {
    public static void main(String[] args) {
        Q q = new Q();
        new Thread(new Producer(q)).start();
        new Thread(new Consumer(q)).start();
    }
}
