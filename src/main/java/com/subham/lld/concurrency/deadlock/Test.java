package com.subham.lld.concurrency.deadlock;


/**
 * Author: the_odd_human
 * Date: 22/02/25
 */
public class Test {
    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        ThreadA threadA = new ThreadA(a, b);
        ThreadB threadB = new ThreadB(a, b);
    }
}
