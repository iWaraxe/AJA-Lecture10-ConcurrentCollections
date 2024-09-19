package com.coherentsolutions.advanced.java.section03;

/**
 * Ex01ConcurrentHashMapJava7 demonstrates the internal structure of ConcurrentHashMap in Java 7.
 * It uses segment-based locking for thread-safe operations.
 */
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Ex01ConcurrentHashMapJava7 {

    public static void main(String[] args) {
        // Creating a ConcurrentHashMap in Java 7 style (segment-based locking)
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        // Thread to update values in the map
        Runnable writerTask = () -> {
            for (int i = 0; i < 1000; i++) {
                map.put("key" + i, i);
            }
        };

        // Thread to read values from the map
        Runnable readerTask = () -> {
            for (int i = 0; i < 1000; i++) {
                Integer value = map.get("key" + i);
                System.out.println("Reading key" + i + ": " + value);
            }
        };

        // ExecutorService to run tasks concurrently
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(writerTask);
        executor.submit(readerTask);

        executor.shutdown();
    }
}
