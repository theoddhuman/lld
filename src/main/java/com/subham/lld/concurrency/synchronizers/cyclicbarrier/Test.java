package com.subham.lld.concurrency.synchronizers.cyclicbarrier;


import java.util.concurrent.CyclicBarrier;

/**
 * Author: the_odd_human
 * Date: 01/03/25
 */
public class Test {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new BarAction());

        new Thread(new MyThread(cyclicBarrier, "A")).start();
        new Thread(new MyThread(cyclicBarrier, "B")).start();
        new Thread(new MyThread(cyclicBarrier, "C")).start();
    }



}
