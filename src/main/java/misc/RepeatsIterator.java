package misc;

import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings({"unused", "rawtypes"})
class RepeatsIterator implements Iterator<Integer> {
    private final Iterator fromIter;
    private int count, currentElement;

    public RepeatsIterator(Iterator from) {
        fromIter = from;
        count = 0;
        currentElement = 0;
        findNext();
    }

    private void findNext() {
        while (count == currentElement && fromIter.hasNext()) {
            currentElement = (Integer) fromIter.next();
            count = 0;
        }
    }

    public boolean hasNext() {
        return count < currentElement;
    }

    public Integer next() {
        if (!hasNext()) throw new NoSuchElementException();
        int result = currentElement;
        count++;
        findNext();
        return result;
    }
}