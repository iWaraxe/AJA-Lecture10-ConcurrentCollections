package com.coherentsolutions.advanced.java.section05;

/**
 * Ex04ProducerConsumerModel demonstrates the producer-consumer model using BlockingQueue.
 * It shows how BlockingQueue simplifies coordination between producers and consumers.
 */
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Ex04ProducerConsumerModel {

    public static void main(String[] args) {
        // Create a bounded queue with capacity 10
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);

        // Producer Runnable
        Runnable producer = () -> {
            try {
                for (int i = 0; i < 50; i++) {
                    queue.put(i);
                    System.out.println(Thread.currentThread().getName() + " produced: " + i);
                    Thread.sleep(50); // Simulate production time
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        // Consumer Runnable
        Runnable consumer = () -> {
            try {
                while (true) {
                    Integer item = queue.take();
                    System.out.println(Thread.currentThread().getName() + " consumed: " + item);
                    Thread.sleep(100); // Simulate consumption time
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        // Start producers
        new Thread(producer, "Producer1").start();
        new Thread(producer, "Producer2").start();

        // Start consumers
        new Thread(consumer, "Consumer1").start();
        new Thread(consumer, "Consumer2").start();
    }
}
