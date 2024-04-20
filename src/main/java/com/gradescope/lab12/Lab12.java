package com.gradescope.lab12;

/* Header
/ Lab 12
/ Names: Sam Yoder, Nathan Dinh, Johnny Nguyen
*/
import java.util.*;

@SuppressWarnings("unused")
public class Lab12 {
    public static int longestSortedSequence(List<Integer> l) {
        // Always return 0 if a list is empty
        if (l.isEmpty()) return 0;

        // Account for the first element
        int biggest = 1;
        int current = 1;

        // Every index up to the second to last
        for (int i = 1; i < l.size(); i++) {
            // Increase the count by 1
            current = l.get(i) > l.get(i - 1) ? current + 1 : 1;
            // Only count sequences of at least two numbers so not individual numbers
            biggest = Math.max(current, biggest);
        }

        return biggest;
    }

    public static void rearrange(Queue<Integer> q) {
        Stack<Integer> stack = new Stack<>();

        // First iteration through - store size in a variable so it doesn't change
        int size = q.size();
        for (int i = 0; i < size; i++) {
            int n = q.remove();
            if (n % 2 == 0) q.add(n); // If a number is even add it back, otherwise put it in the stack
            else stack.add(n);
        }

        // Now we have all the odd numbers, but when we add them back they will be backwards
        while (!stack.isEmpty()) q.add(stack.pop());

        // So we repeat the entire process again which will remove and readd the odd numbers in reverse reverse
        // making them the correct order
        size = q.size();
        for (int i = 0; i < size; i++) {
            int n = q.remove();
            if (n % 2 == 0) q.add(n);
            else stack.add(n);
        }

        while (!stack.isEmpty()) q.add(stack.pop());
    }


    public static void removeEvenLength(Set<String> set) {
        Iterator<String> iterator = set.iterator();
        // Iterators are the only way to remove specific things from a set
        while (iterator.hasNext()) {
            String s = iterator.next();
            // If the length is even, remove it
            if (s.length() % 2 == 0) iterator.remove();
        }
    }

    public static Set<String> whoPassed(Map<String, Integer> students, Map<Integer, Double> grades, double desired) {
        Set<String> passed = new HashSet<>();

        // For each student-percent pair
        for (Map.Entry<String, Integer> student: students.entrySet()) {
            // Check if the actual grade the student got is less than the desired grade
            // and skip if it is, since we only want students who scored above
            if (grades.get(student.getValue()) < desired) continue;

            // Add the passed student's name to the set
            passed.add(student.getKey());
        }

        return passed;
    }
}
