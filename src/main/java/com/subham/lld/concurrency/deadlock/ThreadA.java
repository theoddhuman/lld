package com.subham.lld.concurrency.deadlock;


/**
 * Author: the_odd_human
 * Date: 22/02/25
 */
public class ThreadA implements Runnable{
    A a;
    B b;
    Thread thread;

    public ThreadA(A a, B b) {
        this.a = a;
        this.b = b;
        thread = new Thread(this, "Thread - A");
        thread.start();
    }

    @Override
    public void run() {
        a.foo(b);
        System.out.println("Return back to thread A");
    }
}
