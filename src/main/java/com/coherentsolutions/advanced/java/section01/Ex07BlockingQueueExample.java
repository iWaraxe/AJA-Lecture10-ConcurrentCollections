package com.coherentsolutions.advanced.java.section01;

/**
 * Ex07BlockingQueueExample demonstrates the use of BlockingQueue
 * to implement the producer-consumer pattern without explicit synchronization.
 */
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Ex07BlockingQueueExample {

    public static void main(String[] args) throws InterruptedException {
        // Creating a BlockingQueue with capacity 5
        BlockingQueue<String> queue = new LinkedBlockingQueue<>(5);

        // Producer thread that adds items to the queue
        Thread producer = new Thread(() -> {
            try {
                queue.put("Item 1");
                System.out.println("Produced Item 1");
                Thread.sleep(500); // Simulate time to produce next item
                queue.put("Item 2");
                System.out.println("Produced Item 2");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Consumer thread that retrieves items from the queue
        Thread consumer = new Thread(() -> {
            try {
                Thread.sleep(1000); // Simulate initial delay
                String item = queue.take();
                System.out.println("Consumed " + item);
                item = queue.take();
                System.out.println("Consumed " + item);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Starting both threads
        producer.start();
        consumer.start();

        // Waiting for both threads to finish
        producer.join();
        consumer.join();
    }
}
