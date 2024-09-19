package com.coherentsolutions.advanced.java.section03;

/**
 * Ex04ConcurrentHashMapComputeIfAbsent demonstrates the atomic operation computeIfAbsent.
 * This method computes the value only if the key is not already associated with a value.
 */
import java.util.concurrent.ConcurrentHashMap;

public class Ex04ConcurrentHashMapComputeIfAbsent {

    public static void main(String[] args) {
        // Creating a ConcurrentHashMap
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        // Use computeIfAbsent to compute value only if key is missing
        Integer value = map.computeIfAbsent("Key1", key -> key.length() + 100);
        System.out.println("Computed value for Key1: " + value); // Should print 105 (Key1 length + 100)

        // Add the value for an existing key, computeIfAbsent should not override
        map.put("Key2", 200);
        Integer existingValue = map.computeIfAbsent("Key2", key -> 999);
        System.out.println("Computed value for Key2: " + existingValue); // Should print 200
    }
}
