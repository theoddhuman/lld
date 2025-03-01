package com.subham.lld.concurrency.synchronizers.phaser;


import java.util.concurrent.Phaser;

/**
 * Author: the_odd_human
 * Date: 01/03/25
 */
public class Test {
    public static void main(String[] args) {
        Phaser phaser = new Phaser(1);
        int currentPhase;

        System.out.println("Starting..");
        new Thread(new MyThread(phaser, "A")).start();
        new Thread(new MyThread(phaser, "B")).start();
        new Thread(new MyThread(phaser, "C")).start();

        currentPhase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Completed phase "+ currentPhase);

        currentPhase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Completed phase "+ currentPhase);

        currentPhase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Completed phase "+ currentPhase);
        phaser.arriveAndDeregister();
        if(phaser.isTerminated())
            System.out.println("Phase is terminated");
    }
}
