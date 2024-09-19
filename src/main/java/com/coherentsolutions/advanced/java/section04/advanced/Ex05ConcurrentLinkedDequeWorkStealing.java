package com.coherentsolutions.advanced.java.section04.advanced;

/**
 * Ex05ConcurrentLinkedDequeWorkStealing demonstrates a work-stealing algorithm where
 * worker threads can steal tasks from other threads' deques when their own deque is empty.
 */
import java.util.concurrent.ConcurrentLinkedDeque;

public class Ex05ConcurrentLinkedDequeWorkStealing {

    public static void main(String[] args) {
        ConcurrentLinkedDeque<String> taskDeque1 = new ConcurrentLinkedDeque<>();
        ConcurrentLinkedDeque<String> taskDeque2 = new ConcurrentLinkedDeque<>();

        // Adding tasks to taskDeque1
        taskDeque1.addFirst("Task1");
        taskDeque1.addFirst("Task2");

        // Adding tasks to taskDeque2
        taskDeque2.addFirst("Task3");
        taskDeque2.addFirst("Task4");

        // Worker thread 1 processes tasks from taskDeque1
        Thread worker1 = new Thread(() -> {
            while (!taskDeque1.isEmpty()) {
                String task = taskDeque1.pollFirst();
                if (task != null) {
                    System.out.println("Worker1 executing: " + task);
                } else {
                    // If taskDeque1 is empty, steal from taskDeque2
                    task = taskDeque2.pollLast();
                    if (task != null) {
                        System.out.println("Worker1 stole and executing: " + task);
                    }
                }
            }
        });

        // Worker thread 2 processes tasks from taskDeque2
        Thread worker2 = new Thread(() -> {
            while (!taskDeque2.isEmpty()) {
                String task = taskDeque2.pollFirst();
                if (task != null) {
                    System.out.println("Worker2 executing: " + task);
                } else {
                    // If taskDeque2 is empty, steal from taskDeque1
                    task = taskDeque1.pollLast();
                    if (task != null) {
                        System.out.println("Worker2 stole and executing: " + task);
                    }
                }
            }
        });

        worker1.start();
        worker2.start();
    }
}
