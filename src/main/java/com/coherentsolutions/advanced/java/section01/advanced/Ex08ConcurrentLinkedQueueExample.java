package com.coherentsolutions.advanced.java.section01.advanced;

/**
 * Ex08ConcurrentLinkedQueueExample demonstrates the use of ConcurrentLinkedQueue,
 * a non-blocking, thread-safe queue suitable for high-throughput applications.
 */
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Ex08ConcurrentLinkedQueueExample {

    public static void main(String[] args) throws InterruptedException {
        // Creating a ConcurrentLinkedQueue
        Queue<String> queue = new ConcurrentLinkedQueue<>();

        // Producer thread that adds items to the queue
        Thread producer = new Thread(() -> {
            queue.offer("Task 1");
            System.out.println("Offered Task 1");
            queue.offer("Task 2");
            System.out.println("Offered Task 2");
        });

        // Consumer thread that retrieves items from the queue
        Thread consumer = new Thread(() -> {
            while (true) {
                String task = queue.poll();
                if (task != null) {
                    System.out.println("Processed " + task);
                } else {
                    break;
                }
                // Simulate some processing time
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // Starting both threads
        producer.start();
        producer.join(); // Ensure producer has added items before consumer starts
        consumer.start();
        consumer.join();
    }
}
