package com.gradescope.hw4;

/* Header
/ HW4
/ Name: Sam Yoder
*/

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleIntIterator implements Iterator<Integer> {
    public DoubleIntList list;
    public DoubleIntNode current;
    public int currentIndex;

    public DoubleIntIterator(DoubleIntList list) {
        this.list = list;
        this.current = this.list.getFront();
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
