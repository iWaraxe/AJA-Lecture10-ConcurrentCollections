package com.coherentsolutions.advanced.java.section01;

/**
 * Ex06CopyOnWriteArrayListExample demonstrates the use of CopyOnWriteArrayList
 * in a multi-threaded environment where reads greatly outnumber writes.
 */
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Ex06CopyOnWriteArrayListExample {

    public static void main(String[] args) throws InterruptedException {
        // Creating a CopyOnWriteArrayList
        List<String> cowList = new CopyOnWriteArrayList<>();

        // Adding initial elements
        cowList.add("Element 1");
        cowList.add("Element 2");

        // Reader thread that iterates over the list
        Thread readerThread = new Thread(() -> {
            for (String element : cowList) {
                System.out.println("Read: " + element);
                // Simulate some processing time
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // Writer thread that adds a new element
        Thread writerThread = new Thread(() -> {
            cowList.add("Element 3");
            System.out.println("Added Element 3");
        });

        // Starting both threads
        readerThread.start();
        writerThread.start();

        // Waiting for both threads to finish
        readerThread.join();
        writerThread.join();
    }
}
