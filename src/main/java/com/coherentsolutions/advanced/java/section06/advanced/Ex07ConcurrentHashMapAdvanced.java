package com.coherentsolutions.advanced.java.section60.advanced;

/**
 * Ex07ConcurrentHashMapAdvanced demonstrates advanced atomic operations in ConcurrentHashMap
 * such as computeIfAbsent and merge.
 */
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Ex07ConcurrentHashMapAdvanced {

    public static void main(String[] args) {
        // ConcurrentHashMap with advanced operations
        Map<String, Integer> map = new ConcurrentHashMap<>();

        // computeIfAbsent to lazily initialize a value
        map.computeIfAbsent("Alice", k -> 90);
        System.out.println("After computeIfAbsent: " + map);

        // merge to safely combine values
        map.merge("Bob", 85, Integer::sum);
        map.merge("Alice", 5, Integer::sum); // Adds 5 to Alice's existing score
        System.out.println("After merge: " + map);
    }
}
