package com.coherentsolutions.advanced.java.section05.advanced;

/**
 * Ex05ArrayBlockingQueueWithFairness demonstrates the use of the fairness policy in ArrayBlockingQueue.
 * The fairness policy ensures that threads access the queue in a first-come, first-served manner.
 */
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Ex05ArrayBlockingQueueWithFairness {

    public static void main(String[] args) {
        // Create an ArrayBlockingQueue with fairness policy enabled (true)
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(5, true);

        // Producer Runnable
        Runnable producer = () -> {
            try {
                String threadName = Thread.currentThread().getName();
                for (int i = 1; i <= 5; i++) {
                    String message = threadName + " - Message " + i;
                    queue.put(message); // Blocks if queue is full
                    System.out.println(threadName + " produced: " + message);
                    Thread.sleep(100);
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
                    String message = queue.take(); // Blocks if queue is empty
                    if (message.equals("DONE")) {
                        queue.put("DONE"); // Signal other consumers to stop
                        break;
                    }
                    System.out.println(Thread.currentThread().getName() + " consumed: " + message);
                    Thread.sleep(200); // Simulate processing
                }
                System.out.println(Thread.currentThread().getName() + " terminating.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        // Start multiple producers and consumers with fairness
        Thread producer1 = new Thread(producer, "Producer1");
        Thread producer2 = new Thread(producer, "Producer2");
        Thread consumer1 = new Thread(consumer, "Consumer1");
        Thread consumer2 = new Thread(consumer, "Consumer2");

        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();
    }
}
