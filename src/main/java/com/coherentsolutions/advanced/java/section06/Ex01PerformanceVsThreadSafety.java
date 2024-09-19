package com.coherentsolutions.advanced.java.section60;

/**
 * Ex01PerformanceVsThreadSafety demonstrates the trade-off between performance and thread safety.
 * It highlights how non-thread-safe collections like ArrayList and HashMap provide better performance in single-threaded environments.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ex01PerformanceVsThreadSafety {

    public static void main(String[] args) {
        // Example of non-thread-safe ArrayList
        List<String> names = new ArrayList<>();
        names.add("Alice");
        names.add("Bob");
        System.out.println(names.get(1)); // Outputs: Bob

        // Example of non-thread-safe HashMap
        Map<String, Integer> scores = new HashMap<>();
        scores.put("Alice", 90);
        scores.put("Bob", 85);
        System.out.println(scores.get("Alice")); // Outputs: 90

        // These collections work well in single-threaded environments but are not safe for concurrent access.
    }
}
