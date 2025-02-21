package com.subham.lld.concurrency.deadlock;


/**
 * Author: the_odd_human
 * Date: 22/02/25
 */
public class ThreadB implements Runnable{
    A a;
    B b;
    Thread thread;

    public ThreadB(A a, B b) {
        this.a = a;
        this.b = b;
        thread = new Thread(this, "Thread - B");
        thread.start();
    }

    @Override
    public void run() {
        b.bar(a);
        System.out.println("Return back to thread B");
    }
}
