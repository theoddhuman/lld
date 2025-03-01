package com.subham.lld.concurrency.lock;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Author: the_odd_human
 * Date: 01/03/25
 */
public class Test {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        new Thread(new LockThread(lock, "A")).start();
        new Thread(new LockThread(lock, "B")).start();
    }
}
