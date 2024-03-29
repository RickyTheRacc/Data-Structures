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
        // Start at the front of the list and work up
        this.current = this.list.getFront();
        this.currentIndex = 0;
    }

    @Override
    public boolean hasNext() {
        // We advanced current in the last iteration so if it's null we don't have any more
        return current != null;
    }

    @Override
    public Integer next() {
        if (!hasNext()) throw new NoSuchElementException();

        // Save the node's data before advancing and then return and increase index
        int data = current.data;
        current = current.next;
        currentIndex++;

        return data;
    }

    public void remove() {
        if (currentIndex == 0) return; // Currentindex is only zero if we haven't removed anything yet
        // In which case we can't remove something that doesn't exist

        // Since we're removing nodes that were before all the next ones, everything will be shifted
        // back by one, and we need to shift currentindex back to account for this
        list.remove(--currentIndex);
    }
}
