package com.subham.lld.concurrency.suspension;


/**
 * Author: the_odd_human
 * Date: 22/02/25
 */
public class Test {
    public static void main(String[] args) {
        MyThread a = new MyThread("A");
        MyThread b = new MyThread("B");

        try {
            Thread.sleep(500);
            a.suspend();
            System.out.println("Suspending thread A");
            Thread.sleep(500);
            a.resume();
            System.out.println("Resuming thread A");
            Thread.sleep(500);
            b.suspend();
            System.out.println("Suspending thread B");
            Thread.sleep(599);
            b.resume();
            System.out.println("resuming thread B");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        try {
            a.thread.join();
            b.thread.join();
            System.out.println("Exiting main thread");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
