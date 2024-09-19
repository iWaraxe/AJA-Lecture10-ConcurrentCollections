package com.coherentsolutions.advanced.java.section05;

/**
 * Ex03LinkedBlockingQueueExample demonstrates the use of LinkedBlockingQueue in a scenario with multiple producers and consumers.
 * It showcases how producers and consumers interact in an unbounded queue.
 */
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Ex03LinkedBlockingQueueExample {

    public static void main(String[] args) {
        // Create an unbounded LinkedBlockingQueue
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();

        // Producer Runnable
        Runnable producer = () -> {
            try {
                String threadName = Thread.currentThread().getName();
                for (int i = 1; i <= 5; i++) {
                    String message = threadName + " - Message " + i;
                    queue.put(message);
                    System.out.println(threadName + " Produced: " + message);
                    Thread.sleep(100); // Simulate production time
                }
                queue.put("DONE");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        // Consumer Runnable
        Runnable consumer = () -> {
            try {
                while (true) {
                    String message = queue.take();
                    if (message.equals("DONE")) {
                        queue.put("DONE"); // Pass the termination signal to other consumers
                        break;
                    }
                    System.out.println(Thread.currentThread().getName() + " Consumed: " + message);
                    Thread.sleep(200); // Simulate consumption time
                }
                System.out.println(Thread.currentThread().getName() + " terminating.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        // Start multiple producers
        Thread producer1 = new Thread(producer, "Producer1");
        Thread producer2 = new Thread(producer, "Producer2");
        producer1.start();
        producer2.start();

        // Start multiple consumers
        Thread consumer1 = new Thread(consumer, "Consumer1");
        Thread consumer2 = new Thread(consumer, "Consumer2");
        consumer1.start();
        consumer2.start();
    }
}
