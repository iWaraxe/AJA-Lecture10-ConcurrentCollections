package com.coherentsolutions.advanced.java.section04.advanced;

/**
 * Ex04ConcurrentLinkedQueueAdvanced demonstrates how ConcurrentLinkedQueue
 * can be used in a high-throughput scenario where multiple producers and consumers operate simultaneously.
 */
import java.util.concurrent.ConcurrentLinkedQueue;

public class Ex04ConcurrentLinkedQueueAdvanced {

    public static void main(String[] args) {
        ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();

        // Multiple producer threads
        Thread producer1 = new Thread(() -> {
            for (int i = 1; i <= 500; i++) {
                queue.add(i);
                System.out.println("Producer1 added: " + i);
            }
        });

        Thread producer2 = new Thread(() -> {
            for (int i = 501; i <= 1000; i++) {
                queue.add(i);
                System.out.println("Producer2 added: " + i);
            }
        });

        // Multiple consumer threads
        Thread consumer1 = new Thread(() -> {
            while (!queue.isEmpty()) {
                Integer value = queue.poll();
                if (value != null) {
                    System.out.println("Consumer1 processed: " + value);
                }
            }
        });

        Thread consumer2 = new Thread(() -> {
            while (!queue.isEmpty()) {
                Integer value = queue.poll();
                if (value != null) {
                    System.out.println("Consumer2 processed: " + value);
                }
            }
        });

        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();
    }
}
