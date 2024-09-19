package com.coherentsolutions.advanced.java.section01.advanced;

/**
 * Ex10DeadlockAvoidanceExample shows how concurrent collections help prevent deadlocks
 * by eliminating the need for external synchronization.
 */
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Ex10DeadlockAvoidanceExample {

    public static void main(String[] args) throws InterruptedException {
        // Creating a ConcurrentMap
        ConcurrentMap<String, Integer> concurrentMap = new ConcurrentHashMap<>();

        // Thread A performs operations without external synchronization
        Thread threadA = new Thread(() -> {
            concurrentMap.put("KeyA", 1);
            System.out.println("Thread A added KeyA");
            // Simulate some work
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            Integer value = concurrentMap.get("KeyB");
            System.out.println("Thread A read KeyB: " + value);
        });

        // Thread B performs operations without external synchronization
        Thread threadB = new Thread(() -> {
            concurrentMap.put("KeyB", 2);
            System.out.println("Thread B added KeyB");
            // Simulate some work
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            Integer value = concurrentMap.get("KeyA");
            System.out.println("Thread B read KeyA: " + value);
        });

        // Starting both threads
        threadA.start();
        threadB.start();

        // Waiting for both threads to finish
        threadA.join();
        threadB.join();
    }
}
