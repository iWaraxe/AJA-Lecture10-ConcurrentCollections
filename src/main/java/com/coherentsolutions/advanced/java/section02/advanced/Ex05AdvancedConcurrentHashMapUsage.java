package com.coherentsolutions.advanced.java.section02.advanced;

/**
 * Ex05AdvancedConcurrentHashMapUsage demonstrates advanced features of ConcurrentHashMap,
 * such as atomic methods like computeIfAbsent, putIfAbsent, and remove in a concurrent environment.
 */
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class Ex05AdvancedConcurrentHashMapUsage {

    public static void main(String[] args) {
        // Creating a ConcurrentHashMap with advanced usage of atomic methods
        Map<String, Integer> concurrentMap = new ConcurrentHashMap<>();

        // Using computeIfAbsent to ensure initialization before updating
        Thread initializer = new Thread(() -> {
            String[] keys = {"Key1", "Key2", "Key3"};
            for (String key : keys) {
                concurrentMap.computeIfAbsent(key, k -> 0);
                System.out.println("Initialized: " + key);
            }
        });

        // Using putIfAbsent and remove in another thread
        Thread updater = new Thread(() -> {
            String[] keys = {"Key1", "Key2", "Key3"};
            for (String key : keys) {
                concurrentMap.putIfAbsent(key, 1); // Only put if the key is absent
                Integer removed = concurrentMap.remove(key);
                System.out.println("Removed: " + key + " with value " + removed);
            }
        });

        // Starting both threads
        initializer.start();
        updater.start();
    }
}
