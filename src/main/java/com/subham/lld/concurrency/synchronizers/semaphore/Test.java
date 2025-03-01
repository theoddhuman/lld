package com.subham.lld.concurrency.synchronizers.semaphore;


import java.util.concurrent.Semaphore;

/**
 * Author: the_odd_human
 * Date: 22/02/25
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(1);
        new Thread(new IncrThread(semaphore, "IncrThread")).start();
        Thread.sleep(1000);
        new Thread(new DecrThread(semaphore, "DecrThread")).start();
    }
}
