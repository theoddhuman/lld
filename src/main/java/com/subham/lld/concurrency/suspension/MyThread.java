package com.subham.lld.concurrency.suspension;


/**
 * Author: the_odd_human
 * Date: 22/02/25
 */
public class MyThread implements Runnable {
    private String name;
    Thread thread;
    private boolean suspend;

    public MyThread(String name) {
        this.name = name;
        thread = new Thread(this, name);
        suspend = false;
        thread.start();
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 15; i++) {
                System.out.println(name + ": " + i);
                Thread.sleep(200);
                synchronized (this) {
                    if(suspend) {
                        wait();
                    }
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(name + " exiting");
    }

    public synchronized void suspend() {
        suspend = true;
    }

    public synchronized void resume() {
        suspend = false;
        notify();
    }
}
