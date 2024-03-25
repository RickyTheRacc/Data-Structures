package com.gradescope.lab9;

/* Header
/ Lab 9
/ Names: Sam Yoder, Nathan Dinh, Johnny Nguyen
*/

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EveryOther2<T> implements Iterator<T> {
    private final Iterator<T> iter;
    private boolean hasNext;

    public EveryOther2(Iterator<T> iter){
        this.iter = iter;
        // Makes sure that if the iterator is empty it won't try to get the first element
        this.hasNext = iter.hasNext();
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }

    @Override
    public T next() {
        if (!hasNext()) throw new NoSuchElementException();

        // Store the value to be returned since we can't call next without advancing the iterator
        T next = iter.next();
        hasNext = iter.hasNext();

        // Only skip the next element if it exists
        if (hasNext) iter.next();
        hasNext = iter.hasNext(); // Set hasNext again to prevent a crash if there was only one trailing element

        return next;
    }
}
