package com.coherentsolutions.advanced.java.section03;

/**
 * Ex05ConcurrentHashMapMerge demonstrates the atomic merge operation in ConcurrentHashMap.
 * It combines an existing value with a new one using the provided remapping function.
 */
import java.util.concurrent.ConcurrentHashMap;

public class Ex05ConcurrentHashMapMerge {

    public static void main(String[] args) {
        // Creating a ConcurrentHashMap
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        // Add an initial key-value pair
        map.put("Key1", 10);

        // Using merge to combine the existing value with the new value
        map.merge("Key1", 5, Integer::sum);
        System.out.println("Merged value for Key1: " + map.get("Key1")); // Should print 15

        // Merge a new value for a key that doesn't exist yet
        map.merge("Key2", 7, Integer::sum);
        System.out.println("Value for Key2: " + map.get("Key2")); // Should print 7
    }
}
