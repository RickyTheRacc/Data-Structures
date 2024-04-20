package com.gradescope.lab3;

/*Header
/ Lab 3
/ Names: Sam Yoder, Nathan Dinh, Johnny Nguyen
*/

import java.util.Random;

public class RandomWalker {
    // Create private variables
    private int x, y, steps;

    // Make a public constructor, set everything to zero to start
    public RandomWalker() {
        x = 0;
        y = 0;
        steps = 0;
    }

    // Returns the value of the x variable
    public int getX() {
        return x;
    }

    // Returns the value of the y variable
    public int getY() {
        return y;
    }

    // Returns the value of the steps variable
    public int getSteps() {
        return steps;
    }

    public void move() {
        // Create a new random object
        Random rand = new Random();

        // Roll number between 0 and 3 inclusive
        int roll = rand.nextInt(4);

        if (roll == 0) {        // If roll is 0, move up
            y++;
        } else if (roll == 1) { // If roll is 1, move down
            y--;
        } else if (roll == 2) { // If roll is 2, move left
            x--;
        } else {                // If roll is 3, move right
            x++;
        }

        // Add 1 to steps every time we take a step
        steps++;
    }

    @Override
    public String toString() {
        // Return the x and y coordinates and the number of steps
        return "(" + x + ", " + y + "), steps = " + steps;
    }
}
