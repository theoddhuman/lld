package com.subham.lld.concurrency.synchronizers.phaser;


import java.util.concurrent.Phaser;

/**
 * Author: the_odd_human
 * Date: 01/03/25
 */
public class MyThread implements Runnable{
    Phaser phaser;
    String name;

    public MyThread(Phaser phaser, String name) {
        this.phaser = phaser;
        this.name = name;
        phaser.register();
    }

    @Override
    public void run() {
        System.out.println("Thread " + name + " starting phase 0");
        phaser.arriveAndAwaitAdvance();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Thread " + name + " starting phase 1");
        phaser.arriveAndAwaitAdvance();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Thread " + name + " starting phase 2");
        phaser.arriveAndDeregister();
    }
}
