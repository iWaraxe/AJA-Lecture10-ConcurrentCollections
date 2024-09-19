package com.coherentsolutions.advanced.java.section01.advanced;

/**
 * Ex09WeakConsistencyExample demonstrates the concept of weak consistency in concurrent collections.
 * Iterators may not reflect all concurrent modifications.
 */
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Ex09WeakConsistencyExample {

    public static void main(String[] args) throws InterruptedException {
        // Creating a ConcurrentHashMap
        Map<String, String> concurrentMap = new ConcurrentHashMap<>();
        concurrentMap.put("Key1", "Value1");
        concurrentMap.put("Key2", "Value2");

        // Thread that iterates over the map
        Thread iteratorThread = new Thread(() -> {
            Iterator<Map.Entry<String, String>> iterator = concurrentMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                System.out.println("Iterated: " + entry.getKey() + " = " + entry.getValue());
                // Simulate some processing time
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // Thread that modifies the map
        Thread modifierThread = new Thread(() -> {
            try {
                Thread.sleep(150); // Ensure iterator starts first
                concurrentMap.put("Key3", "Value3");
                System.out.println("Added Key3 = Value3");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Starting both threads
        iteratorThread.start();
        modifierThread.start();

        // Waiting for both threads to finish
        iteratorThread.join();
        modifierThread.join();
    }
}
