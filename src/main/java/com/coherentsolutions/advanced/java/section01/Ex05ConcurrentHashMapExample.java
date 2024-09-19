package com.coherentsolutions.advanced.java.section01;

/**
 * Ex05ConcurrentHashMapExample demonstrates the use of ConcurrentHashMap
 * to overcome the limitations of synchronized collections.
 */
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Ex05ConcurrentHashMapExample {

    public static void main(String[] args) throws InterruptedException {
        // Creating a ConcurrentHashMap
        Map<String, Integer> concurrentMap = new ConcurrentHashMap<>();

        // Writer thread adds key-value pairs
        Thread writerThread = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                concurrentMap.put("Key" + i, i);
                // Simulate some delay
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // Reader thread retrieves key-value pairs
        Thread readerThread = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                Integer value = concurrentMap.get("Key" + i);
                if (value != null) {
                    System.out.println("Read " + "Key" + i + ": " + value);
                }
                // Simulate some processing time
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // Starting both threads
        writerThread.start();
        readerThread.start();

        // Waiting for both threads to finish
        writerThread.join();
        readerThread.join();
    }
}
