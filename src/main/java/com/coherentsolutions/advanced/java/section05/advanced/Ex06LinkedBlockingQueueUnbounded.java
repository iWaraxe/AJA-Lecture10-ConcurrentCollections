package com.coherentsolutions.advanced.java.section05.advanced;

/**
 * Ex06LinkedBlockingQueueUnbounded demonstrates the use of LinkedBlockingQueue as an unbounded queue.
 * No capacity limit is specified, allowing the queue to grow dynamically.
 */
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Ex06LinkedBlockingQueueUnbounded {

    public static void main(String[] args) {
        // Create an unbounded LinkedBlockingQueue
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

        // Producer Runnable
        Runnable producer = () -> {
            try {
                for (int i = 1; i <= 100; i++) {
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

        // Start producer and consumer threads
        new Thread(producer, "Producer").start();
        new Thread(consumer, "Consumer").start();
    }
}
