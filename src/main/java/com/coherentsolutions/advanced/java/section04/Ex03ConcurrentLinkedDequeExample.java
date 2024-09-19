package com.coherentsolutions.advanced.java.section04;

/**
 * Ex03ConcurrentLinkedDequeExample demonstrates the use of ConcurrentLinkedDeque
 * in a task scheduling scenario where tasks are processed from both ends of the deque.
 */
import java.util.concurrent.ConcurrentLinkedDeque;

public class Ex03ConcurrentLinkedDequeExample {

    public static void main(String[] args) {
        ConcurrentLinkedDeque<String> taskDeque = new ConcurrentLinkedDeque<>();

        // Adding initial tasks
        taskDeque.addFirst("HighPriorityTask1");
        taskDeque.addLast("LowPriorityTask1");

        // High priority worker (processes from the front)
        Thread highPriorityWorker = new Thread(() -> {
            while (!taskDeque.isEmpty()) {
                String task = taskDeque.pollFirst();
                if (task != null) {
                    System.out.println("High Priority Worker executing: " + task);
                    try {
                        Thread.sleep(100); // Simulate task execution
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });

        // Low priority worker (processes from the end)
        Thread lowPriorityWorker = new Thread(() -> {
            while (!taskDeque.isEmpty()) {
                String task = taskDeque.pollLast();
                if (task != null) {
                    System.out.println("Low Priority Worker executing: " + task);
                    try {
                        Thread.sleep(150); // Simulate task execution
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });

        highPriorityWorker.start();
        lowPriorityWorker.start();

        // Adding more tasks dynamically
        taskDeque.addFirst("HighPriorityTask2");
        taskDeque.addLast("LowPriorityTask2");
    }
}
