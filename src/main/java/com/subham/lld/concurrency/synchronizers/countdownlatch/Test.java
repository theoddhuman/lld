package com.subham.lld.concurrency.synchronizers.countdownlatch;


import java.util.concurrent.CountDownLatch;

/**
 * Author: the_odd_human
 * Date: 01/03/25
 */
public class Test {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        Thread thread = new Thread(new MyThread(countDownLatch));
        System.out.println("Starting thread");
        thread.start();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Done");
    }
}
