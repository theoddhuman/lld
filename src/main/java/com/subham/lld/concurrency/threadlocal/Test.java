package com.subham.lld.concurrency.threadlocal;


/**
 * Author: the_odd_human
 * Date: 22/02/25
 */
public class Test {
    public static void main(String[] args) {
        ParentThread parentThread = new ParentThread();
        parentThread.start();
    }
}
