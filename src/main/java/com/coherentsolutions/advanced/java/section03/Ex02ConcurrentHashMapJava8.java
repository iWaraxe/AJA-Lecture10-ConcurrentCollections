package com.coherentsolutions.advanced.java.section03;

/**
 * Ex02ConcurrentHashMapJava8 demonstrates the internal structure of ConcurrentHashMap in Java 8+.
 * It highlights the use of bucket-level locking and lock-free reads using CAS operations.
 */
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Ex02ConcurrentHashMapJava8 {

    public static void main(String[] args) {
        // Creating a ConcurrentHashMap with bucket-level locking and CAS-based read operations
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        // Writer task to update values in the map
        Runnable writerTask = () -> {
            for (int i = 0; i < 1000; i++) {
                map.put("key" + i, i);
            }
        };

        // Reader task to read values from the map using lock-free read
        Runnable readerTask = () -> {
            for (int i = 0; i < 1000; i++) {
                Integer value = map.get("key" + i);
                if (value != null) {
                    System.out.println("Reading key" + i + ": " + value);
                }
            }
        };

        // Using an ExecutorService to run the tasks concurrently
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(writerTask);
        executor.submit(readerTask);

        executor.shutdown();
    }
}
