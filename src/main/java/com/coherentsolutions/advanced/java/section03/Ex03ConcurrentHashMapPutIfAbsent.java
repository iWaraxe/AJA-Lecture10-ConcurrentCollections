package com.coherentsolutions.advanced.java.section03;

/**
 * Ex03ConcurrentHashMapPutIfAbsent demonstrates the use of atomic methods like putIfAbsent.
 * This method ensures that a value is only added if the key is not already present.
 */
import java.util.concurrent.ConcurrentHashMap;

public class Ex03ConcurrentHashMapPutIfAbsent {

    public static void main(String[] args) {
        // Creating a ConcurrentHashMap
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

        // Adding an initial key-value pair
        map.put("User1", "Alice");

        // Using putIfAbsent to add only if the key is not present
        String previousValue = map.putIfAbsent("User1", "Bob");
        System.out.println("Previous value: " + previousValue); // Should print "Alice"

        // Add a new key-value pair since the key "User2" is not present
        map.putIfAbsent("User2", "Charlie");
        System.out.println("User2 value: " + map.get("User2")); // Should print "Charlie"
    }
}
