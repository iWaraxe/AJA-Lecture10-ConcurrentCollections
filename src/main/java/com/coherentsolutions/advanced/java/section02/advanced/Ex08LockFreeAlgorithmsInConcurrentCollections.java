package com.coherentsolutions.advanced.java.section02.advanced;

/**
 * Ex08LockFreeAlgorithmsInConcurrentCollections demonstrates the internal use of lock-free algorithms
 * in ConcurrentLinkedQueue by simulating a high-concurrency scenario.
 */
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.Queue;

public class Ex08LockFreeAlgorithmsInConcurrentCollections {

    public static void main(String[] args) {
        // Creating a ConcurrentLinkedQueue to demonstrate lock-free algorithms
        Queue<Integer> concurrentQueue = new ConcurrentLinkedQueue<>();

        // Adding elements to the queue in multiple threads
        Thread producer1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                concurrentQueue.offer(i);
            }
        });

        Thread producer2 = new Thread(() -> {
            for (int i = 100; i < 200; i++) {
                concurrentQueue.offer(i);
            }
        });

        // Consumer thread that retrieves elements
        Thread consumer = new Thread(() -> {
            while (!concurrentQueue.isEmpty()) {
                Integer value = concurrentQueue.poll(); // Lock-free operation
                if (value != null) {
                    System.out.println("Consumed: " + value);
                }
            }
        });

        // Starting producer and consumer threads
        producer1.start();
        producer2.start();
        consumer.start();
    }
}
