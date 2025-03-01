package com.subham.lld.concurrency.synchronizers.countdownlatch;


import java.util.concurrent.CountDownLatch;

/**
 * Author: the_odd_human
 * Date: 01/03/25
 */
public class MyThread implements Runnable {
    CountDownLatch countDownLatch;

    public MyThread(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        for(int i=0; i<5; i++) {
            System.out.println(i);
            countDownLatch.countDown();
        }
    }
}
