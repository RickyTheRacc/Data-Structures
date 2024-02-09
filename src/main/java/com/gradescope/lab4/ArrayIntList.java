package com.gradescope.lab4;

import java.util.Arrays;

/*Header
/Lab 4
/Names: Sam Yoder,
*/
public class ArrayIntList {
    private int[] list;
    private int size;
    public static final int DEFAULT_CAPACITY = 100;

    public ArrayIntList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayIntList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity: " + capacity);
        }
        list = new int[capacity];
        size = 0;
    }

    public int size() {
        return size;
    }

    public int get(int index) {
        checkIndex(index);
        return list[index];
    }

    public String toString() {
        if (size == 0) return "[]";
        String result = "[";

        for (int i = 0; i < size; i++) {
            if (i != 0) result += ", ";
            result += list[i];
        }

        return result + "]";
    }

    public int indexOf(int value) {
        for (int i = 0; i < size; i++) {
            if (list[i] == value) {
                return i;
            }
        }
        return -1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(int value) {
        return indexOf(value) >= 0;
    }

    public void add(int value) {
        checkCapacity(size + 1);
        list[size] = value;
        size++;
    }
    
    // Inserts the given value at the given index, shifting subsequent values right
    public void add(int index, int value) {
        //checks index is in bounds and trows error otherwise
        checkIndex(index);
        //checks we have space for one more element in array
        checkCapacity(size + 1);
        for (int i = size; i > index; i--) {
            list[i] = list[i - 1];
        }
        list[index] = value;
        size++;
    }

    // Removes value at the given index, shifting subsequent values in the list left
    public void remove(int index) {
        //checks index is in bounds and trows error otherwise
        checkIndex(index);
        for (int i = index; i < size - 1; i++) {
            list[i] = list[i + 1];
        }
        size--;
    }

    // Replaces the integer at the given index with the given value
    public void set(int index, int value) {
        //checks index is in bounds and trows error otherwise
        checkIndex(index);
        list[index] = value;
    }

    // Removes all elements from the list (not array)
    public void clear() {
        size = 0;
    }

    // Checks that array has the given capacity
    // throws an IllegalStateException otherwise
    private void checkCapacity(int capacity) {
        //list.length is actual array list
        if (capacity > list.length) {
            throw new IllegalStateException("would exceed list capacity");
        }
    }

    // Checks if the index inside the bound of actual list size
    // throws IndexOutOfBound exception otherwise
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index: " + index);
        }
    }

    //Lab 4 part 2

    // Write a method removeAll that accepts an integer value as a parameter
    //and that removes all occurrences of the given value from the list.
    // Do not use a for each loop

    public void removeAll(int value) {
        int i = 0;

        // Loop through every item in the list
        while (i < size) {
            // Remove if the value is the same, otherwise keep going
            if (list[i] == value) {
                remove(i);
            } else {
                i++;
            }
        }
    }

    public ArrayIntList runningTotal() {
        ArrayIntList result = new ArrayIntList(size);

        int i = 0;
        // Loop through every item in the list
        while (i < size) {
            int j = 0;
            int sum = 0;

            // Sum every item up to and including the item at list[i]
            while (j <= i) {
                sum += list[j];
                j++;
            }

            // Add the sum to the result
            result.add(sum);
            i++;
        }

        return result;
    }
}