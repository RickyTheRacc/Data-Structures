package com.gradescope.lab9;

/* Header
/ Lab 9
/ Names: Sam Yoder, Nathan Dinh, Johnny Nguyen
*/

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIterator implements Iterator<Integer> {
    private final Integer[] input;
    private int current;

    public EvenIterator(Integer[] input) {
        this.input = input;
        // Start at the beginning
        this.current = 0;
    }

    @Override
    public boolean hasNext() {
        // Worst case scenario it goes through the entire array
        while (current < input.length) {
            // If the current element is even break the loop
            if (input[current] % 2 == 0) return true;
            current++; // Otherwise iterate current
        }

        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) throw new NoSuchElementException();

        // Current is already set to the index of the next even number by hasNext
        // We need to advance it by one though or it will keep returning the first
        // even element in the array
        return input[current++];
    }
}
