package com.coherentsolutions.advanced.java.section01;

/**
 * Ex03ReadWriteBlockingExample illustrates how read operations are blocked by write operations
 * in synchronized collections, leading to performance degradation.
 */
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Ex03ReadWriteBlockingExample {

    public static void main(String[] args) throws InterruptedException {
        // Creating a synchronized list
        List<Integer> synchronizedList = Collections.synchronizedList(new ArrayList<>());

        // Adding initial elements to the list
        for (int i = 0; i < 1000; i++) {
            synchronizedList.add(i);
        }

        // Writer thread that adds elements to the list
        Thread writerThread = new Thread(() -> {
            for (int i = 1000; i < 2000; i++) {
                synchronizedList.add(i);
                // Simulate some delay
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // Multiple reader threads that read elements from the list
        Thread readerThread1 = new Thread(() -> readList(synchronizedList, "Reader 1"));
        Thread readerThread2 = new Thread(() -> readList(synchronizedList, "Reader 2"));

        // Starting all threads
        writerThread.start();
        readerThread1.start();
        readerThread2.start();

        // Waiting for all threads to finish
        writerThread.join();
        readerThread1.join();
        readerThread2.join();
    }

    /**
     * Reads elements from the synchronized list.
     *
     * @param list The synchronized list to read from.
     * @param readerName The name of the reader thread.
     */
    private static void readList(List<Integer> list, String readerName) {
        synchronized (list) {
            for (Integer value : list) {
                System.out.println(readerName + " read value: " + value);
                // Simulate some processing time
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
