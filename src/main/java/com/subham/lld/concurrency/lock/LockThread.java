package com.subham.lld.concurrency.lock;


import java.util.concurrent.locks.Lock;

/**
 * Author: the_odd_human
 * Date: 01/03/25
 */
public class LockThread implements Runnable {
    Lock lock;
    String name;

    public LockThread(Lock lock, String name) {
        this.lock = lock;
        this.name = name;
    }


    @Override
    public void run() {
        try {
            System.out.println("Thread " + name + " trying to acquire lock");
            lock.lock();
            System.out.println("Thread " + name + " acquired lock");
            Shared.count++;
            System.out.println("Thread " + name + ": " + Shared.count);
            System.out.println("Thread " + name + " sleeping");
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("Thread " + name + " releasing lock");
            lock.unlock();
        }
    }
}
