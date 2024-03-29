package misc;

import java.util.Iterator;

@SuppressWarnings("unused")
public class EvenIterator implements Iterator<Integer> {
    private int current;

    public EvenIterator() {
        this.current = 2;
    }

    public boolean hasNext() {
        return true;
    }

    public Integer next() {
        int temp = current;
        current += 2;
        return temp;
    }
}
