package com.wioletta;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter number of threads: ");
            int numThreads = scanner.nextInt();

            long iterations = 1000000000L;

            long iterationsPerThread = iterations / numThreads;

            Thread[] threads = new Thread[numThreads];
            ParallelMonteCarloPi[] estimators = new ParallelMonteCarloPi[numThreads];

            long startTime = System.nanoTime();

            for (int i = 0; i < numThreads; i++) {
                estimators[i] = new ParallelMonteCarloPi(iterationsPerThread);
                threads[i] = new Thread(estimators[i]);
                threads[i].start();
            }

            long insideCircle = 0;
            for (int i = 0; i < numThreads; i++) {
                try {
                    threads[i].join();
                    insideCircle += estimators[i].getInsideCircle();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            double pi = 4.0 * insideCircle / iterations;

            long endTime = System.nanoTime();
            double duration = (endTime - startTime) / 1_000_000_000.0;

            System.out.printf("PI is %.5f\n", pi);
            System.out.println("THREADS " + numThreads);
            System.out.println("ITERATIONS " + iterations);
            System.out.printf("TIME %.5fs\n", duration);
        } catch (Exception e) {
            System.out.println("Invalid input! Please enter a valid number of threads.");
        }
    }
}