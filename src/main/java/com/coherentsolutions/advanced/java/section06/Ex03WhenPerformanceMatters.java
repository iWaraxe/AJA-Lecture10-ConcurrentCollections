package com.coherentsolutions.advanced.java.section60;

/**
 * Ex03WhenPerformanceMatters demonstrates the use of ArrayList, HashMap, and HashSet in performance-critical scenarios.
 * It shows how these collections offer fast access and minimal overhead when thread safety is not a concern.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Ex03WhenPerformanceMatters {

    public static void main(String[] args) {
        // ArrayList - Fast random access and efficient for adding elements at the end
        List<String> names = new ArrayList<>();
        names.add("Alice");
        names.add("Bob");
        System.out.println(names.get(1)); // Outputs: Bob

        // HashMap - Constant-time performance for basic operations like get() and put()
        Map<String, Integer> scores = new HashMap<>();
        scores.put("Alice", 90);
        scores.put("Bob", 85);
        System.out.println(scores.get("Alice")); // Outputs: 90

        // HashSet - Constant-time performance for add, remove, and contains
        Set<String> uniqueNames = new HashSet<>();
        uniqueNames.add("Alice");
        uniqueNames.add("Bob");
        uniqueNames.add("Alice"); // Duplicate, won't be added
        System.out.println(uniqueNames.size()); // Outputs: 2
    }
}
