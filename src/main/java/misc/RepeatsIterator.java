package misc;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

@SuppressWarnings({"unused", "rawtypes"})
class RepeatsIterator implements Iterator {
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

    public Object next() {
        if (!hasNext()) throw new NoSuchElementException();
        int result = currentElement;
        count++;
        findNext();
        return result;
    }

    public static void main(String[] args) {
        List l = new LinkedList();
        l.add(3);
        l.add(1);
        l.add(2);
        l.add(0);
        Iterator i = new RepeatsIterator(l.iterator());
        while (i.hasNext()) {
            System.out.println(i.next());
        }
    }
}