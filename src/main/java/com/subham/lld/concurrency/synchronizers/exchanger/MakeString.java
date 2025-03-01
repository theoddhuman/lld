package com.subham.lld.concurrency.synchronizers.exchanger;


import java.util.concurrent.Exchanger;

/**
 * Author: the_odd_human
 * Date: 01/03/25
 */
public class MakeString implements Runnable{
    Exchanger<String> exchanger;

    public MakeString(Exchanger<String> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        char ch = 'A';
        for(int i=0; i<3; i++) {
            String s = "";
            for(int j=0; j<5; j++) {
                s += ch++;
            }
            try {
                exchanger.exchange(s);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
