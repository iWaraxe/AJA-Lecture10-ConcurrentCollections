package com.coherentsolutions.advanced.java.section60.advanced;

/**
 * Ex06CustomComparatorInTreeSet demonstrates the use of a custom comparator in TreeSet
 * to control the sorting order of elements.
 */
import java.util.Set;
import java.util.TreeSet;
import java.util.Comparator;

public class Ex06CustomComparatorInTreeSet {

    public static void main(String[] args) {
        // Custom comparator to sort strings by length
        Set<String> customSortedSet = new TreeSet<>(Comparator.comparingInt(String::length));
        customSortedSet.add("Apple");
        customSortedSet.add("Banana");
        customSortedSet.add("Kiwi");

        System.out.println("TreeSet with custom comparator (sorted by length):");
        for (String fruit : customSortedSet) {
            System.out.println(fruit);
        }
    }
}
