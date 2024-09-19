package com.coherentsolutions.advanced.java.section60;

/**
 * Ex02MemoryUsage demonstrates the impact of memory overhead when choosing collections like LinkedHashMap and HashMap.
 * It shows how memory consumption can increase when maintaining order with LinkedHashMap.
 */
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Ex02MemoryUsage {

    public static void main(String[] args) {
        // Example of HashMap (no ordering)
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("Alice", 90);
        hashMap.put("Bob", 85);
        hashMap.put("Charlie", 95);

        System.out.println("HashMap:");
        for (String name : hashMap.keySet()) {
            System.out.println(name);
        }

        // Example of LinkedHashMap (maintains insertion order)
        Map<String, Integer> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("Alice", 90);
        linkedHashMap.put("Bob", 85);
        linkedHashMap.put("Charlie", 95);

        System.out.println("\nLinkedHashMap (insertion order):");
        for (String name : linkedHashMap.keySet()) {
            System.out.println(name);
        }

        // LinkedHashMap consumes more memory due to additional ordering functionality.
    }
}
