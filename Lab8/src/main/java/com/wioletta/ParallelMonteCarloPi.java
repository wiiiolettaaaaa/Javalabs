package com.wioletta;

import java.util.concurrent.ThreadLocalRandom;

public class ParallelMonteCarloPi implements Runnable {
    private final long iterations;
    private long insideCircle = 0;

    public ParallelMonteCarloPi(long iterations) {
        this.iterations = iterations;
    }

    @Override
    public void run() {
        for (long i = 0; i < iterations; i++) {
            double x = ThreadLocalRandom.current().nextDouble();
            double y = ThreadLocalRandom.current().nextDouble();

            if (x * x + y * y <= 1) insideCircle++;
        }
    }

    public long getInsideCircle() {
        return insideCircle;
    }
}
