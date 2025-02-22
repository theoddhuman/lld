package com.subham.lld.concurrency.produceconsumer.semaphore;


/**
 * Author: the_odd_human
 * Date: 22/02/25
 */
public class Producer implements Runnable{
    private Q q;

    public Producer(Q q) {
        this.q = q;
    }

    @Override
    public void run() {
        for(int i=1;i<=20;i++) {
            q.put(i);
        }
    }
}
