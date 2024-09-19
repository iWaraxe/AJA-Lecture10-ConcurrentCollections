package com.coherentsolutions.advanced.java.section03;

/**
 * Ex06ConcurrentHashMapPerformanceComparison compares the performance between a synchronized HashMap
 * and a ConcurrentHashMap under concurrent access using multiple threads.
 */
import java.util.*;
import java.util.concurrent.*;

public class Ex06ConcurrentHashMapPerformanceComparison {

    public static void main(String[] args) throws InterruptedException {
        final int THREAD_COUNT = 100;
        final int OPERATIONS_PER_THREAD = 10000;

        // Synchronized HashMap
        Map<Integer, Integer> synchronizedMap = Collections.synchronizedMap(new HashMap<>());

        // ConcurrentHashMap
        Map<Integer, Integer> concurrentMap = new ConcurrentHashMap<>();

        // Measure performance of synchronized HashMap
        long syncMapTime = measurePerformance(synchronizedMap, THREAD_COUNT, OPERATIONS_PER_THREAD);
        System.out.println("Synchronized HashMap time: " + syncMapTime + " ms");

        // Measure performance of ConcurrentHashMap
        long concurrentMapTime = measurePerformance(concurrentMap, THREAD_COUNT, OPERATIONS_PER_THREAD);
        System.out.println("ConcurrentHashMap time: " + concurrentMapTime + " ms");
    }

    private static long measurePerformance(Map<Integer, Integer> map, int threadCount, int operationsPerThread) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        long startTime = System.currentTimeMillis();

        // Create and start threads
        for (int i = 0; i < threadCount; i++) {
            executor.execute(() -> {
                Random random = new Random();
                for (int j = 0; j < operationsPerThread; j++) {
                    // Mix of read and write operations
                    int key = random.nextInt(1000);
                    map.put(key, random.nextInt(1000));
                    map.get(key);
                }
                latch.countDown();
            });
        }

        // Wait for all threads to finish
        latch.await();
        executor.shutdown();

        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
}
