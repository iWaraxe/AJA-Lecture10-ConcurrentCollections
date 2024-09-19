package com.coherentsolutions.advanced.java.section02;

/**
 * Ex01IntroductionToConcurrentCollections demonstrates the creation and usage of ConcurrentHashMap.
 * It highlights the core functionality of the `java.util.concurrent` package and how it handles concurrency efficiently.
 */
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class Ex01IntroductionToConcurrentCollections {

    public static void main(String[] args) {
        // Creating a ConcurrentHashMap for concurrent access
        Map<String, Integer> wordCounts = new ConcurrentHashMap<>();

        // Thread to increment word counts in the map
        Thread updater = new Thread(() -> {
            String[] words = {"apple", "banana", "orange", "apple", "banana"};
            for (String word : words) {
                // Using merge to safely update the word count
                wordCounts.merge(word, 1, Integer::sum);
            }
        });

        // Thread to read the word counts
        Thread reader = new Thread(() -> {
            try {
                // Introducing some delay to simulate asynchronous behavior
                Thread.sleep(100);
                // Reading the current word counts
                System.out.println("Word Counts: " + wordCounts);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Starting both threads
        updater.start();
        reader.start();
    }
}
