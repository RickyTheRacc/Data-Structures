package com.gradescope.lab2pr1;

import java.util.Arrays;
import static java.lang.Math.*;

public class Lab2 {
    public static void main(String[] args){

        // Count Tests

        int[] arr = {3, 5, 2, 1, 92, 38, 3, 14, 5, 73, 5};
        System.out.println(count(arr, 3));    // Returns 2
        System.out.println(count(arr, 5));    // Returns 3
        System.out.println(count(arr, 12));   // Returns 0
        
        // Duplicates Tests

        System.out.println(duplicates(new String[] {}));                                             // Returns false
        System.out.println(duplicates(new String[]{"Hello"}));                                       // Returns false
        System.out.println(duplicates(new String[] {"Hello","hello"}));                              // Returns false
        System.out.println(duplicates(new String[] {"Hello","apple","winter","Apple","Hello"}));     // Returns true

        // Stretch

        System.out.println(Arrays.toString(stretch(new int[] {})));              // Returns {}
        System.out.println(Arrays.toString(stretch(new int[] {13})));            // Returns {7, 6}
        System.out.println(Arrays.toString(stretch(new int[] {-9})));            // Returns {-4, -5}
        System.out.println(Arrays.toString(stretch(new int[] {5, 8, 3, 2})));    // Returns {3, 2, 4, 4, 2, 1, 1, 1}
    }

    /**
     * @param arr The array of integers to search.
     * @param val The integer number to search for.
     * @return The number of occurrences of val in arr.
     */

    public static int count(int[] arr, int val) {
        // Variable which represents how many times we found the val
        int sum = 0;

        // Iterate through every value in the array
        for (int i: arr) {
            // If the int equals val, increase the sum
            if (i == val) sum++;
        }

        return sum;
    }

    /**
     * @param arr The array of strings to search.
     * @return Whether the array contains case-sensitive duplicate strings.
     */
	
    public static boolean duplicates(String[] arr) {
        // Sort the array so if there are duplicates, they will be right beside each other
        Arrays.sort(arr);

        // Iterate through every value in the array
        for (int i = 0; i < arr.length - 1; i++) {
            // Check each string with the string ahead of it
            // If they match, return true which automatically breaks the loop
            if (arr[i].equals(arr[i + 1])) return true;
        }

        // Return false if no duplicates are found
        return false;
    }

    /**
     * @param arr The initial array of ints
     * @return The stretched array
     */
    
    public static int[] stretch(int[] arr) {
        // Make a new array with twice the original length
        int[] newArr = new int[arr.length * 2];

        // Iterate through every value in the array
        for (int i = 0; i < arr.length; i++) {
            // Calculate the indices to put the new elements
            int index = (2 * i);

            // Add the larger element first, if an odd number
            newArr[index] = (int) Math.ceil(arr[i] / 2.0);
            // Add the smaller element second, if an odd number
            newArr[index + 1] = (int) Math.floor(arr[i] / 2.0);
        }

        return newArr;
    }
}
