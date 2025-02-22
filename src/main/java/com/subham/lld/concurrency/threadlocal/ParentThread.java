package com.subham.lld.concurrency.threadlocal;


import com.subham.lld.messageQueue.Publisher;

/**
 * Author: the_odd_human
 * Date: 22/02/25
 */
public class ParentThread extends Thread {
    public static InheritableThreadLocal<String> parentValue = new InheritableThreadLocal<>() {
       public String childValue(String parentValue) {
           return "child data";
       }
    };

    @Override
    public void run() {
        parentValue.set("Parent Data");
        System.out.println( "Parent data : " + parentValue.get());
        ChildThread childThread = new ChildThread();
        childThread.start();
    }
}
