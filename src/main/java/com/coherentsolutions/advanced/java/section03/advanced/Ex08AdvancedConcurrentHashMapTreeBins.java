package com.coherentsolutions.advanced.java.section03.advanced;

/**
 * Ex08AdvancedConcurrentHashMapTreeBins demonstrates the usage of tree bins in ConcurrentHashMap for handling hash collisions.
 * When a bucket gets too full, the list of nodes transforms into a balanced tree.
 */
import java.util.concurrent.ConcurrentHashMap;

public class Ex08AdvancedConcurrentHashMapTreeBins {

    public static void main(String[] args) {
        // Creating a ConcurrentHashMap that can use tree bins for hash collision handling
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

        // Adding entries with similar hash codes to trigger tree bin transformation
        for (int i = 0; i < 100; i++) {
            map.put("Key" + i, "Value" + i);
        }

        // Accessing some entries
        for (int i = 0; i < 10; i++) {
            System.out.println("Key" + i + " = " + map.get("Key" + i));
        }
    }
}
