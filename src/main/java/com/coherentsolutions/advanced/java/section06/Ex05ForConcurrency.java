package com.coherentsolutions.advanced.java.section60;

/**
 * Ex05ForConcurrency demonstrates the use of ConcurrentHashMap and ConcurrentLinkedQueue
 * for thread-safe operations in multi-threaded environments.
 */
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Ex05ForConcurrency {

    public static void main(String[] args) {
        // ConcurrentHashMap - Thread-safe HashMap with high concurrency
        Map<String, Integer> concurrentMap = new ConcurrentHashMap<>();
        concurrentMap.put("Alice", 90);
        concurrentMap.compute("Bob", (k, v) -> (v == null) ? 85 : v + 1); // Thread-safe update
        System.out.println("ConcurrentHashMap:");
        System.out.println(concurrentMap);

        // ConcurrentLinkedQueue - Thread-safe, non-blocking queue for high-throughput applications
        Queue<String> messageQueue = new ConcurrentLinkedQueue<>();
        messageQueue.add("Message1");
        System.out.println("\nConcurrentLinkedQueue:");
        System.out.println("Polled: " + messageQueue.poll()); // Removes and returns the head of the queue
    }
}
