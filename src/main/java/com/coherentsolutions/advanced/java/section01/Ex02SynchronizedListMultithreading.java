package com.coherentsolutions.advanced.java.section01;

/**
 * Ex02SynchronizedListMultithreading demonstrates the limitations of synchronized collections
 * when accessed by multiple threads, highlighting the contention caused by a single lock.
 */
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Ex02SynchronizedListMultithreading {

    public static void main(String[] args) throws InterruptedException {
        // Creating a synchronized list
        List<Integer> synchronizedList = Collections.synchronizedList(new ArrayList<>());

        // Writer thread that adds elements to the list
        Thread writerThread = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                synchronizedList.add(i);
                // Simulate some delay
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // Reader thread that reads elements from the list
        Thread readerThread = new Thread(() -> {
            synchronized (synchronizedList) {
                for (Integer value : synchronizedList) {
                    System.out.println("Read value: " + value);
                    // Simulate some processing time
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });

        // Starting both threads
        writerThread.start();
        readerThread.start();

        // Waiting for both threads to finish
        writerThread.join();
        readerThread.join();
    }
}
