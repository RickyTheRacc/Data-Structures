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

        for (char c: s.toLowerCase().toCharArray()) {
            if (punctuation.contains(String.valueOf(c))) continue;
            result.append(c);
        }

        return result.toString();
    }

    public static TreeMap<String, Integer> words(String inputFileName) {
        TreeMap<String, Integer> words = new TreeMap<>();
        Scanner scanner;

        try {
            scanner = new Scanner(new File(inputFileName));
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            return words;
        }

        while (scanner.hasNext()) {
            String word = punctuation(scanner.next());
            if (word.isEmpty()) continue;
            words.put(word, words.getOrDefault(word, 0) + 1);
        }

        return words;
    }

    public static void statistics(TreeMap<String, Integer> map) {
        if (map.isEmpty()) return;

        System.out.println("Number of different words in book: " + map.size());

        // Stream API woohoo!!
        int totalWords = map.values().stream().mapToInt(Integer::intValue).sum();
        System.out.println("Total number of in book: " + totalWords);

        // Stream API part 2
        Comparator<String> common = Comparator.comparingInt(map::get);
        String mostCommonWord = map.keySet().stream().max(common).orElse(null);
        System.out.println("Most common word in book: " + mostCommonWord);

        // Stream API part :3
        Comparator<String> length = Comparator.comparingInt(String::length);
        String longestWord = map.keySet().stream().max(length).orElse(null);
        System.out.println("Longest word in book: " + longestWord);

        System.out.println("Is occurs in book: " + map.getOrDefault("is", 0) + " times");
    }
}
