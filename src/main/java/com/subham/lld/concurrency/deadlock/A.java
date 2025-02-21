package com.subham.lld.concurrency.deadlock;


/**
 * Author: the_odd_human
 * Date: 22/02/25
 */
public class A {

    synchronized void foo(B b) {
        String name = Thread.currentThread().getName();
        System.out.println(name + " inside A.foo");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Trying to call b.last()");
        b.last();
    }

    synchronized void last() {
        System.out.println("Inside A.last()");
    }
}
