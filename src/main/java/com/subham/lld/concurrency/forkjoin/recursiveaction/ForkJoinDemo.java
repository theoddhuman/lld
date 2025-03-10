package com.subham.lld.concurrency.forkjoin.recursiveaction;


import java.util.concurrent.ForkJoinPool;

/**
 * Author: the_odd_human
 * Date: 05/03/25
 */
public class ForkJoinDemo {
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        double[] data = new double[100000];
        for(int i=0; i<data.length; i++) {
            data[i] = i;
        }
        System.out.println("original sequence upto 10");
        for(int i=0; i<10; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();

        SqrtTransform sqrtTransform = new SqrtTransform(data, 0, data.length);
        forkJoinPool.invoke(sqrtTransform);
        System.out.println(forkJoinPool.getParallelism());

        System.out.println("Updated sequence upto 10");
        for(int i=0; i<10; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }
}
