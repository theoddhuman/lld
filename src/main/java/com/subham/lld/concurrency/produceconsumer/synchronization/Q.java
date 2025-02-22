package com.subham.lld.concurrency.produceconsumer.synchronization;


/**
 * Author: the_odd_human
 * Date: 20/02/25
 */
public class Q {
    private int n;

    private boolean valueSet = false;

    public synchronized int get() {
        try {
            while (!valueSet) {
                wait();
            }
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Interrupted get");
        }
        this.valueSet = false;
        System.out.println("Get: " + n);
        notify();
        return n;
    }

    public synchronized void put(int n) {
        try {
            while (valueSet) {
                wait();
            }
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Interrupted put");
        }
        this.n = n;
        this.valueSet = true;
        System.out.println("Put: " + n);
        notify();
    }
}
