package com.coherentsolutions.advanced.java.section02;

/**
 * Ex02CopyOnWriteArrayListExample demonstrates the usage of CopyOnWriteArrayList in a multi-threaded environment.
 * It shows how CopyOnWriteArrayList handles concurrent modifications without throwing ConcurrentModificationException.
 */
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.List;

public class Ex02CopyOnWriteArrayListExample {

    public static void main(String[] args) {
        // Creating a CopyOnWriteArrayList
        List<String> userList = new CopyOnWriteArrayList<>();

        // Adding initial users to the list
        userList.add("Alice");
        userList.add("Bob");

        // Thread to read from the list
        Thread reader = new Thread(() -> {
            for (String user : userList) {
                System.out.println("User: " + user);
                try {
                    Thread.sleep(50); // Simulating some processing time
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // Thread to add a new user to the list
        Thread writer = new Thread(() -> {
            try {
                Thread.sleep(100); // Simulating delay
                userList.add("Charlie");
                System.out.println("Added Charlie");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Starting both threads
        reader.start();
        writer.start();
    }
}
