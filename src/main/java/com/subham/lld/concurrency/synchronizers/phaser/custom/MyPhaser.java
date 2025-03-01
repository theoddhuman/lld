package com.subham.lld.concurrency.synchronizers.phaser.custom;


import java.util.concurrent.Phaser;

/**
 * Author: the_odd_human
 * Date: 01/03/25
 */
public class MyPhaser extends Phaser {
    int numParties;
    int phases;

    public MyPhaser(int numParties, int phases) {
        super(numParties);
        this.numParties = numParties;
        this.phases = phases;
    }

    @Override
    public boolean onAdvance(int p, int numParties) {
        System.out.println("Phase " + p + " completed.");
        if(p == phases-1 || numParties == 0) {
            return true;
        }
        return false;
    }
}
