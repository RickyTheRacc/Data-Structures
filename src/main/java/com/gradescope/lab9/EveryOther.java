package com.gradescope.lab9;

/* Header
/ Lab 9
/ Names: Sam Yoder, Nathan Dinh, Johnny Nguyen
*/

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EveryOther<T> implements Iterator<T> {
    private int current;
    private final int end;
    private final T[] input;

    public EveryOther(int start, int end, T[] input){
        this.current = start; //
        this.end = end;
        this.input = input;
    }

    @Override
    public boolean hasNext() {
        return this.current <= end; // Has to be <= or it will always terminate on the first call
    }

    @Override
    public T next() {
        if (!hasNext()) throw new NoSuchElementException();

        T temp = input[current];
        current += 2; // Supposed to be a 2 since going to every other element
        return temp;
    }
}
