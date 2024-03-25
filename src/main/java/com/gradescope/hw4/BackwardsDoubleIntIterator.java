package com.gradescope.hw4;

/* Header
/ HW4
/ Name: Sam Yoder
*/

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BackwardsDoubleIntIterator implements Iterator<Integer> {
    public DoubleIntList list;
    public DoubleIntNode current;
    public int currentIndex;

    public BackwardsDoubleIntIterator(DoubleIntList list) {
        this.list = list;
        this.current = this.list.getEnd();
        this.currentIndex = list.size() - 1;
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
        if (currentIndex == list.size() - 1) return;
        list.remove(currentIndex + 1);
    }
}