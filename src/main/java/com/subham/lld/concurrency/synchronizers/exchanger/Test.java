package com.subham.lld.concurrency.synchronizers.exchanger;


import java.util.concurrent.Exchanger;

/**
 * Author: the_odd_human
 * Date: 01/03/25
 */
public class Test {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        new Thread(new MakeString(exchanger)).start();
        new Thread(new UseString(exchanger)).start();
    }
}
