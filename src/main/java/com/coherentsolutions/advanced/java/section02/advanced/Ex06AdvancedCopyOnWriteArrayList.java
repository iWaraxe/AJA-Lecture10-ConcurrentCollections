package com.coherentsolutions.advanced.java.section02.advanced;

/**
 * Ex06AdvancedCopyOnWriteArrayList demonstrates the internal mechanism of how CopyOnWriteArrayList handles
 * concurrent modifications efficiently by copying the array on each write.
 */
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.List;

public class Ex06AdvancedCopyOnWriteArrayList {

    public static void main(String[] args) {
        // Creating a CopyOnWriteArrayList and adding elements
        List<String> list = new CopyOnWriteArrayList<>();
        list.add("Item1");
        list.add("Item2");

        // Writer thread modifying the list
        Thread writer = new Thread(() -> {
            list.add("Item3");
            System.out.println("Added Item3");
            list.remove("Item2");
            System.out.println("Removed Item2");
        });

        // Reader thread iterating over the list
        Thread reader = new Thread(() -> {
            for (String item : list) {
                System.out.println("Reading: " + item);
            }
        });

        // Starting both threads
        reader.start();
        writer.start();
    }
}
