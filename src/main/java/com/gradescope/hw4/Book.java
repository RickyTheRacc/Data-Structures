package com.gradescope.hw4;

/* Header
/ HW4
/ Name: Sam Yoder
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Book {
    public static String punctuation(String s) {
        StringBuilder result = new StringBuilder();
        String punctuation = ".,\"'!?";

        // Lowercase the string and iterate over the characters
        for (char c: s.toLowerCase().toCharArray()) {
            // Was going to use Character.isLetter() but that oesn't account for numbers
            // Only add characters that aren't in the list of banned characters
            if (punctuation.contains(String.valueOf(c))) continue;
            result.append(c);
        }

        return result.toString();
    }

    public static TreeMap<String, Integer> words(String inputFileName) {
        TreeMap<String, Integer> words = new TreeMap<>();
        Scanner scanner;

        try {
            // Try to create a new scanner with the input file
            scanner = new Scanner(new File(inputFileName));
        } catch (FileNotFoundException e) {
            // If the file isn't found, just return the empty Map
            System.out.println("An error occurred.");
            return words;
        }

        //
        while (scanner.hasNext()) {
            String word = punctuation(scanner.next());
            if (word.isEmpty()) continue; // Account for weird punctuation or formatting
            // Get or default to make sure that it always has a value
            words.put(word, words.getOrDefault(word, 0) + 1);
        }

        return words;
    }

    public static void statistics(TreeMap<String, Integer> map) {
        // If the map is empty, we don't have anything to analyze so just stop
        if (map.isEmpty()) return;

        // Number of entries in the map is directly equivalent to the number of words so we
        // can just use map.size()
        System.out.println("Number of different words in book: " + map.size());

        // Side note, we don't really *need* the .orElse(null) calls on the stream API since we know that
        // the map isn't empty, really just to keep intellij from being mad

        // Use stream API to collect all the integers from the map and add them together
        int totalWords = map.values().stream().mapToInt(Integer::intValue).sum();
        System.out.println("Total number of in book: " + totalWords);

        // Comparator that compares two strings based on the number associated with that string in the map
        Comparator<String> common = Comparator.comparingInt(map::get);
        // Collect all the string from the map and find the string with the biggest associated number
        String mostCommonWord = map.keySet().stream().max(common).orElse(null);
        System.out.println("Most common word in book: " + mostCommonWord);

        // Another comparator but this one compares strings based on the length method
        Comparator<String> length = Comparator.comparingInt(String::length);
        // Collect all the strings and compare based on length to find the longest word
        String longestWord = map.keySet().stream().max(length).orElse(null);
        System.out.println("Longest word in book: " + longestWord);

        // Get the number of times "is" occurred from the map, or zero if is didn't occur in the book
        System.out.println("Is occurs in book: " + map.getOrDefault("is", 0) + " times");
    }
}
