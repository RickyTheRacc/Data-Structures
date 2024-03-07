package misc;

@SuppressWarnings({"unchecked", "unused"})
public class StackArray<T> {
    T[] array;
    int size;

    public StackArray() {
        array = (T[]) new Object[100];
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void push(T item) {
        size = size++;
        array[size] = item;
    }

    public T pop() {
        size = size--;
        return array[size];
    }
}
