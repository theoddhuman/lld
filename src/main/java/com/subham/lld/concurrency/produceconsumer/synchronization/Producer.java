package com.subham.lld.concurrency.produceconsumer.synchronization;


/**
 * Author: the_odd_human
 * Date: 20/02/25
 */
public class Producer implements Runnable{
    private int i;
    private Q q;
    private Thread thread;

    public Producer(Q q) {
        this.q = q;
        i = 0;
        this.thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (true) {
            q.put(++i);
        }
    }
}
