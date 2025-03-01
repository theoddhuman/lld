package com.subham.lld.concurrency.synchronizers.phaser.custom;


/**
 * Author: the_odd_human
 * Date: 01/03/25
 */
public class MyTest {
    public static void main(String[] args) {
        MyPhaser phaser = new MyPhaser(1, 5);
        new Thread(new MyLoopThread(phaser, "A")).start();
        new Thread(new MyLoopThread(phaser, "B")).start();
        new Thread(new MyLoopThread(phaser, "C")).start();

        while(!phaser.isTerminated()) {
            phaser.arriveAndAwaitAdvance();
        }
        if(phaser.isTerminated()) {
            System.out.println("Phaser is terminated");
        }
    }
}
