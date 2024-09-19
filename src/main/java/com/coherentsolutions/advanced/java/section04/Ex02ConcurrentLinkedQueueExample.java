package com.coherentsolutions.advanced.java.section04;

/**
 * Ex02ConcurrentLinkedQueueExample demonstrates the use of ConcurrentLinkedQueue
 * in a producer-consumer scenario where messages are produced and consumed concurrently.
 */
import java.util.concurrent.ConcurrentLinkedQueue;

public class Ex02ConcurrentLinkedQueueExample {

    public static void main(String[] args) {
        ConcurrentLinkedQueue<String> messageQueue = new ConcurrentLinkedQueue<>();

        // Producer Thread
        Thread producer = new Thread(() -> {
            String[] messages = {"Hello", "World", "Concurrent", "Queue"};
            for (String message : messages) {
                messageQueue.add(message);
                System.out.println("Produced: " + message);
                try {
                    Thread.sleep(100); // Simulate work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // Consumer Thread
        Thread consumer = new Thread(() -> {
            while (true) {
                String message = messageQueue.poll();
                if (message != null) {
                    System.out.println("Consumed: " + message);
                } else {
                    if (!producer.isAlive()) { // If producer has finished and the queue is empty
                        break;
                    }
                }
            }
        });

        producer.start();
        consumer.start();
    }
}
