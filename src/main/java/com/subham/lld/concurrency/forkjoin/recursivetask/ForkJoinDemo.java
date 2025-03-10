package com.subham.lld.concurrency.forkjoin.recursivetask;


import com.subham.lld.concurrency.forkjoin.recursiveaction.SqrtTransform;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

/**
 * Author: the_odd_human
 * Date: 05/03/25
 */
public class ForkJoinDemo {
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        double[] data = new double[100000];
        Arrays.fill(data, 2);
        System.out.println("original sequence upto 10");
        for(int i=0; i<10; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();

        SumTask sumTask = new SumTask(data, 0, data.length);
        System.out.println(forkJoinPool.invoke(sumTask));
    }
}
