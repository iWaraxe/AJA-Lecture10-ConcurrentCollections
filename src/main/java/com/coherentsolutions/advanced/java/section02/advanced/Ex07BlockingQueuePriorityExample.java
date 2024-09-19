package com.coherentsolutions.advanced.java.section02.advanced;

/**
 * Ex07BlockingQueuePriorityExample demonstrates the use of PriorityBlockingQueue,
 * an unbounded queue that orders elements according to their natural ordering or a custom comparator.
 */
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Ex07BlockingQueuePriorityExample {

    public static void main(String[] args) {
        // Creating a PriorityBlockingQueue for tasks based on priority
        BlockingQueue<String> priorityQueue = new PriorityBlockingQueue<>();

        // Adding tasks to the priority queue
        priorityQueue.add("Task2");
        priorityQueue.add("Task1");
        priorityQueue.add("Task3");

        // Consumer thread to process tasks from the queue based on priority
        Thread consumer = new Thread(() -> {
            try {
                while (!priorityQueue.isEmpty()) {
                    String task = priorityQueue.take(); // Tasks are processed based on natural order
                    System.out.println("Processed: " + task);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Starting the consumer thread
        consumer.start();
    }
}
