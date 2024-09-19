package com.coherentsolutions.advanced.java.section01.advanced;

/**
 * Ex04SynchronizedCollectionAdvanced demonstrates the potential for deadlocks
 * when using synchronized collections with external synchronization.
 */
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Ex04SynchronizedCollectionAdvanced {

    public static void main(String[] args) throws InterruptedException {
        // Creating a synchronized list
        List<String> synchronizedList = Collections.synchronizedList(new ArrayList<>());

        // Thread A acquires lock on synchronizedList and then tries to acquire lock on another object
        Thread threadA = new Thread(() -> {
            synchronized (synchronizedList) {
                System.out.println("Thread A acquired lock on synchronizedList");
                // Simulate some work
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                synchronized (Ex04SynchronizedCollectionAdvanced.class) {
                    System.out.println("Thread A acquired lock on Ex04SynchronizedCollectionAdvanced.class");
                }
            }
        });

        // Thread B acquires lock on Ex04SynchronizedCollectionAdvanced.class and then tries to acquire lock on synchronizedList
        Thread threadB = new Thread(() -> {
            synchronized (Ex04SynchronizedCollectionAdvanced.class) {
                System.out.println("Thread B acquired lock on Ex04SynchronizedCollectionAdvanced.class");
                // Simulate some work
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                synchronized (synchronizedList) {
                    System.out.println("Thread B acquired lock on synchronizedList");
                }
            }
        });

        // Starting both threads
        threadA.start();
        threadB.start();

        // Waiting for both threads to finish
        threadA.join();
        threadB.join();
    }
}
