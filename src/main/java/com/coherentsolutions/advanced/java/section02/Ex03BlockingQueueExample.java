package com.coherentsolutions.advanced.java.section02;

/**
 * Ex03BlockingQueueExample demonstrates the use of BlockingQueue with producer-consumer pattern.
 * It shows how the queue can block operations if it's full (for producer) or empty (for consumer).
 */
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Ex03BlockingQueueExample {

    public static void main(String[] args) {
        // Creating a LinkedBlockingQueue with a fixed capacity of 5
        BlockingQueue<String> messageQueue = new LinkedBlockingQueue<>(5);

        // Producer thread that adds messages to the queue
        Thread producer = new Thread(() -> {
            String[] messages = {"First", "Second", "Third", "Fourth", "Fifth", "Sixth"};
            try {
                for (String msg : messages) {
                    messageQueue.put(msg); // Blocking call if the queue is full
                    System.out.println("Produced: " + msg);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Consumer thread that retrieves messages from the queue
        Thread consumer = new Thread(() -> {
            try {
                while (true) {
                    String msg = messageQueue.take(); // Blocking call if the queue is empty
                    System.out.println("Consumed: " + msg);
                    if (msg.equals("Sixth")) break; // Terminate after the last message
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Starting both threads
        producer.start();
        consumer.start();
    }
}
