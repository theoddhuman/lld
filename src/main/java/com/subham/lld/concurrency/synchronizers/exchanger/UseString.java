package com.subham.lld.concurrency.synchronizers.exchanger;


import java.util.concurrent.Exchanger;

/**
 * Author: the_odd_human
 * Date: 01/03/25
 */
public class UseString implements Runnable {
    Exchanger<String> exchanger;

    public UseString(Exchanger<String> exchanger) {
        this.exchanger =exchanger;
    }

    @Override
    public void run() {
        for(int i=0; i<3; i++) {
            try {
                String s = exchanger.exchange("");
                System.out.println("Got: "+s);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
