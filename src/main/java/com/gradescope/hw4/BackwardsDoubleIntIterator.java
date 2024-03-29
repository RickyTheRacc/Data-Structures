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
        // Same thing as the last one but start at the end instead of the front
        this.current = this.list.getEnd();
        this.currentIndex = list.size() - 1;
    }

    @Override
    public boolean hasNext() {
        // We advanced the node in the previous iteration so if it's null we don't have any more
        return current != null;
    }

    @Override
    public Integer next() {
        if (!hasNext()) throw new NoSuchElementException();

        // Store the node's data before returning and then advance
        int data = current.data;
        current = current.prev;
        currentIndex--;

        return data;
    }

    public void remove() {
        if (currentIndex == list.size() - 1) return; // If we haven't given an element to be removed yet, don't try
        list.remove(currentIndex + 1); // Since we're removing elements in *front* of the node, we don't
        // need to shift back by one
    }
}