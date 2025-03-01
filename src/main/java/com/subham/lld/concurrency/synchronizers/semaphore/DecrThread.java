package com.subham.lld.concurrency.synchronizers.semaphore;


import java.util.concurrent.Semaphore;

/**
 * Author: the_odd_human
 * Date: 22/02/25
 */
public class DecrThread implements Runnable{
    private Semaphore semaphore;
    private String name;

    public DecrThread(Semaphore semaphore, String name) {
        this.semaphore = semaphore;
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("Starting " + name);
        try {
            System.out.println(name + " trying to acquire lock");
            semaphore.acquire();
            for (int i = 4; i >= 0; i--) {
                Shared.count--;
                System.out.println(name +": "+Shared.count);
                Thread.sleep(1000);
            }
            semaphore.release();
            System.out.println(name + " released lock");
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
        System.out.println("Exiting " + name);
    }
}
