package com.subham.lld.concurrency.synchronizers.phaser.custom;


/**
 * Author: the_odd_human
 * Date: 01/03/25
 */
public class MyLoopThread implements Runnable{
    MyPhaser phaser;
    String name;

    public MyLoopThread(MyPhaser phaser, String name) {
        this.phaser = phaser;
        this.name = name;
        phaser.register();
    }

    @Override
    public void run() {
        while(!phaser.isTerminated()) {
            System.out.println("Thread " + name + " starting phase 0");
            phaser.arriveAndAwaitAdvance();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
