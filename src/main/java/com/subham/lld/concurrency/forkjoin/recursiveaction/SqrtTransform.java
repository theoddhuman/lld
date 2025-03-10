package com.subham.lld.concurrency.forkjoin.recursiveaction;

import java.util.concurrent.RecursiveAction;


/**
 * Author: the_odd_human
 * Date: 05/03/25
 */
public class SqrtTransform extends RecursiveAction {
    final int sqrtThreshold = 1000;
    double[] data;
    int start;
    int end;

    public SqrtTransform(double[] data, int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if(end - start < sqrtThreshold) {
            for(int i= start; i<end; i++) {
                data[i] = Math.sqrt(data[i]);
            }
        } else {
            int middle = (start+end)/2;
            invokeAll(new SqrtTransform(data, start, middle), new SqrtTransform(data, middle, end));
        }
    }
}
