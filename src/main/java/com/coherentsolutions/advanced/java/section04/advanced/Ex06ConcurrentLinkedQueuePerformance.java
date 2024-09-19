package com.coherentsolutions.advanced.java.section04.advanced;

/**
 * Ex06ConcurrentLinkedQueuePerformance demonstrates performance measurement of ConcurrentLinkedQueue
 * in a scenario where multiple threads add and remove elements concurrently.
 */
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Ex06ConcurrentLinkedQueuePerformance {

    public static void main(String[] args) throws InterruptedException {
        ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();
        int threadCount = 10;
        int operationsPerThread = 100000;

        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        long startTime = System.currentTimeMillis();

        // Submitting producer tasks
        for (int i = 0; i < threadCount / 2; i++) {
            executor.submit(() -> {
                for (int j = 0; j < operationsPerThread; j++) {
                    queue.add(j);
                }
            });
        }

        // Submitting consumer tasks
        for (int i = 0; i < threadCount / 2; i++) {
            executor.submit(() -> {
                for (int j = 0; j < operationsPerThread; j++) {
                    queue.poll();
                }
            });
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
            Thread.sleep(100);
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Total time: " + (endTime - startTime) + " ms");
    }
}
