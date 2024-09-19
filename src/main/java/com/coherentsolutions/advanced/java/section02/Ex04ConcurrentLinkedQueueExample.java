package com.coherentsolutions.advanced.java.section02;

/**
 * Ex04ConcurrentLinkedQueueExample demonstrates the use of ConcurrentLinkedQueue in a high-throughput application.
 * It showcases non-blocking operations for enqueueing and dequeueing tasks.
 */
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.Queue;

public class Ex04ConcurrentLinkedQueueExample {

    public static void main(String[] args) {
        // Creating a ConcurrentLinkedQueue for high-throughput operations
        Queue<String> taskQueue = new ConcurrentLinkedQueue<>();

        // Adding tasks to the queue
        taskQueue.add("Task1");
        taskQueue.add("Task2");

        // Worker thread to process tasks from the queue
        Thread worker = new Thread(() -> {
            String task;
            while ((task = taskQueue.poll()) != null) {
                System.out.println("Processing " + task);
            }
        });

        // Starting the worker thread
        worker.start();
    }
}
