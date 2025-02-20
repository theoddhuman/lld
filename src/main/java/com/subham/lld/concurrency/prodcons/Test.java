package com.subham.lld.concurrency.prodcons;


/**
 * Author: the_odd_human
 * Date: 20/02/25
 */
public class Test {
    public static void main(String[] args) {
        Q q = new Q();
        Producer producer = new Producer(q);
        Consumer consumer = new Consumer(q);
    }
}
