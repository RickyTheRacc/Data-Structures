package com.gradescope.hw4;

/* Header
/ HW4
/ Name: Sam Yoder
*/

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BackwardsDoubleIntIterator implements Iterator<Integer> {
    private final DoubleIntList list;
    private DoubleIntNode current;
    private int currentIndex;

    public BackwardsDoubleIntIterator(DoubleIntList list) {
        this.list = list;
        this.current = list.end;
        this.currentIndex = list.size - 1;
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public Integer next() {
        if (!hasNext()) throw new NoSuchElementException();

        int data = current.data;
        current = current.prev;
        currentIndex--;

        return data;
    }

    public void remove() {
        if (currentIndex == list.size - 1) return;
        list.remove(currentIndex + 1);
    }
}