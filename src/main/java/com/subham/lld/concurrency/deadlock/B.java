package com.subham.lld.concurrency.deadlock;


/**
 * Author: the_odd_human
 * Date: 22/02/25
 */
public class B {
    synchronized void bar(A a) {
        String name = Thread.currentThread().getName();
        System.out.println(name + " inside B.bar()");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Trying to call a.last()");
        a.last();
    }
    synchronized void last() {
        System.out.println("Inside B.last()");
    }
}
