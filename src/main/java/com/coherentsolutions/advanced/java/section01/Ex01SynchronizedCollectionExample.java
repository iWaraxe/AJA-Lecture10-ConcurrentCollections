package com.coherentsolutions.advanced.java.section01;

/**
 * Ex01SynchronizedCollectionExample demonstrates how to create and use a synchronized collection in Java.
 * It shows how to wrap an ArrayList using Collections.synchronizedList and the necessity of external synchronization during iteration.
 */
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Ex01SynchronizedCollectionExample {

    public static void main(String[] args) {
        // Creating a synchronized list by wrapping an ArrayList
        List<String> synchronizedList = Collections.synchronizedList(new ArrayList<>());

        // Adding elements to the synchronized list
        synchronizedList.add("Alice");
        synchronizedList.add("Bob");

        // Iterating over the synchronized list
        // External synchronization is required during iteration
        synchronized (synchronizedList) {
            for (String name : synchronizedList) {
                System.out.println(name);
            }
        }
    }
}
