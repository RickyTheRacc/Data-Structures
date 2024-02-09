package com.gradescope.lab3;

/*Header
/ Lab 3
/ Names: Sam Yoder, Nathan Dinh, Johnny Nguyen
*/

import java.util.Scanner;

public class TestRandomWalker {
    public static void main(String[] args){
        // Create a new RandomWalker object using the new keyword
        RandomWalker r = new RandomWalker();

        System.out.println("Type integer number to represent number of steps to take");
        // Create a new scanner object using the system input
        Scanner scanner = new Scanner(System.in);
        // Set the number of steps to the next integer input
        int numberOfSteps = scanner.nextInt();

        // Loop through the number of steps
        for (int i = 0; i < numberOfSteps; i++) {
            // Move the random walker in a random direction
            r.move();
            System.out.println(r); // Print the walker after each step
        }
    }
}
