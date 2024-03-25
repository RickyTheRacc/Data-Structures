package com.gradescope.hw4;

/* Header
/ HW4
/ Name: Sam Yoder
*/

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleIntIterator implements Iterator<Integer> {
    private final DoubleIntList list;
    private DoubleIntNode current;
    private int currentIndex;

    public DoubleIntIterator(DoubleIntList list) {
        this.list = list;
        this.current = list.front;
        this.currentIndex = 0;
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public Integer next() {
        if (!hasNext()) throw new NoSuchElementException();

        int data = current.data;
        current = current.next;
        currentIndex++;

        return data;
    }

    public void remove() {
        if (currentIndex == 0) return;
        list.remove(--currentIndex);
    }
}
