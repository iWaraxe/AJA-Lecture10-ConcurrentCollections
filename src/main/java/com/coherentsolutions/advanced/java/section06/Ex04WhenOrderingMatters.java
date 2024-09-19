package com.coherentsolutions.advanced.java.section60;

/**
 * Ex04WhenOrderingMatters demonstrates the use of LinkedHashMap, TreeSet, and LinkedList
 * to maintain element ordering in collections.
 */
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.List;

public class Ex04WhenOrderingMatters {

    public static void main(String[] args) {
        // LinkedHashMap - Maintains insertion order
        Map<String, Integer> linkedScores = new LinkedHashMap<>();
        linkedScores.put("Alice", 90);
        linkedScores.put("Bob", 85);
        linkedScores.put("Charlie", 95);

        System.out.println("LinkedHashMap (insertion order):");
        for (String name : linkedScores.keySet()) {
            System.out.println(name);
        }

        // TreeSet - Maintains sorted order (natural or custom comparator)
        Set<Integer> sortedNumbers = new TreeSet<>();
        sortedNumbers.add(5);
        sortedNumbers.add(1);
        sortedNumbers.add(3);

        System.out.println("\nTreeSet (sorted order):");
        for (Integer number : sortedNumbers) {
            System.out.println(number);
        }

        // LinkedList - Maintains order and allows efficient insertion/removal at both ends
        List<String> tasks = new LinkedList<>();
        tasks.add("Task1");
        tasks.add(0, "UrgentTask"); // Add at the beginning

        System.out.println("\nLinkedList:");
        System.out.println(tasks); // Outputs: [UrgentTask, Task1]
    }
}
