package com.subham.lld.concurrency.produceconsumer.semaphore;


import java.util.concurrent.Semaphore;

/**
 * Author: the_odd_human
 * Date: 22/02/25
 */
public class Q {
    private int n;
    private Semaphore semCons;
    private Semaphore semProd;

    public Q() {
        n = 0;
        semCons = new Semaphore(0);
        semProd = new Semaphore(1);
    }

    public void get() {
        try {
            semCons.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Got: " + n);
        semProd.release();
    }

    public void put(int n) {
        try {
            semProd.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.n = n;
        System.out.println("Put: " + n);
        semCons.release();
    }
}
