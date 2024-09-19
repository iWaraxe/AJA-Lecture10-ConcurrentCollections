package com.coherentsolutions.advanced.java.section05;

/**
 * Ex02ArrayBlockingQueueExample demonstrates the use of ArrayBlockingQueue
 * in a producer-consumer model. The queue has a fixed capacity.
 */
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Ex02ArrayBlockingQueueExample {

    public static void main(String[] args) {
        // Create a bounded queue with capacity 5
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(5);

        // Producer Thread
        Thread producer = new Thread(() -> {
            int messageCount = 10;
            try {
                for (int i = 1; i <= messageCount; i++) {
                    String message = "Message " + i;
                    queue.put(message); // Blocks if queue is full
                    System.out.println("Produced: " + message);
                }
                queue.put("DONE"); // Indicate completion
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Consumer Thread
        Thread consumer = new Thread(() -> {
            try {
                String message;
                while (!(message = queue.take()).equals("DONE")) { // Blocks if queue is empty
                    System.out.println("Consumed: " + message);
                    Thread.sleep(500); // Simulate processing time
                }
                System.out.println("Consumer received DONE signal, terminating.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();
    }
}
