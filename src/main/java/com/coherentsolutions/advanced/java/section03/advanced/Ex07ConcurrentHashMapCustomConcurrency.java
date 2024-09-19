package com.coherentsolutions.advanced.java.section03.advanced;

/**
 * Ex07ConcurrentHashMapCustomConcurrency demonstrates the configuration of a custom concurrency level
 * in ConcurrentHashMap, using a constructor that allows setting an initial capacity and concurrency level.
 */
import java.util.concurrent.ConcurrentHashMap;

public class Ex07ConcurrentHashMapCustomConcurrency {

    public static void main(String[] args) {
        // Creating a ConcurrentHashMap with initial capacity and custom concurrency level
        int initialCapacity = 16;
        float loadFactor = 0.75f;
        int concurrencyLevel = 8; // Allows up to 8 threads to update the map simultaneously

        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>(initialCapacity, loadFactor, concurrencyLevel);

        // Adding some entries
        map.put("User1", 100);
        map.put("User2", 200);

        // Reading entries
        System.out.println("User1 score: " + map.get("User1"));
        System.out.println("User2 score: " + map.get("User2"));
    }
}
