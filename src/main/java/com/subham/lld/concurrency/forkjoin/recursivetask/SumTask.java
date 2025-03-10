package com.subham.lld.concurrency.forkjoin.recursivetask;

import java.util.concurrent.RecursiveTask;


/**
 * Author: the_odd_human
 * Date: 05/03/25
 */
public class SumTask extends RecursiveTask<Double> {
    final int sqrtThreshold = 1000;
    double[] data;
    int start;
    int end;

    public SumTask(double[] data, int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Double compute() {
        double sum = 0;
        if(end - start < sqrtThreshold) {
            for(int i= start; i<end; i++) {
                sum += data[i];
            }
        } else {
            int middle = (start+end)/2;
            sum += new SumTask(data, start, middle).invoke() + new SumTask(data, middle, end).invoke();
        }
        return sum;
    }
}
