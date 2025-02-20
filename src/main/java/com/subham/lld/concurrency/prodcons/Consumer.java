package com.subham.lld.concurrency.prodcons;


/**
 * Author: the_odd_human
 * Date: 20/02/25
 */
public class Consumer implements Runnable {
    private Q q;
    Thread thread;

    public Consumer(Q q) {
        this.q = q;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while(true) {
            q.get();
        }
    }
}
