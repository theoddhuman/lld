package com.subham.lld.concurrency.synchronizers.cyclicbarrier;


/**
 * Author: the_odd_human
 * Date: 01/03/25
 */
public class BarAction implements Runnable {
    @Override
    public void run() {
        System.out.println("Executed final step");
    }
}
