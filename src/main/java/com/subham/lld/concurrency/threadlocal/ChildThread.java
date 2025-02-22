package com.subham.lld.concurrency.threadlocal;


/**
 * Author: the_odd_human
 * Date: 22/02/25
 */
public class ChildThread extends Thread {

    @Override
    public void run() {
        System.out.println("Child data: " + ParentThread.parentValue.get());
    }
}
