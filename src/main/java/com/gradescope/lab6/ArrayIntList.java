package com.gradescope.lab6;

/*Header
/Lab 6
/Names: Sam Yoder, Johnny Nguyen, Nathan Dinh
*/

import java.util.Arrays;

public class ArrayIntList implements IntList{
    private int[] list;
    private int size;
    
    public ArrayIntList() {
        list = new int[100]; //create an array with capacity of 100
        this.size=0; //the actual list has no elements yet
    }
    
    public ArrayIntList(int capacity) {
        list = new int[capacity];
        this.size = 0;
    }
	
	/*add method stretch 1*/

    public void stretch1() {
        for (int i = 0; i < size; i++) {
            add(i, list[i]); //add the same element after the current element
            i++; //skip the next element, since we're going by two
        }
    }
	
	/*add method stretch2*/
	
	public void stretch2() {
        // Create a new array with double the capacity
        int[] temp = new int[list.length * 2];

        // Go through each element from the original array
        // Add it to the same index, plus the next index
        for (int i = 0; i < size; i++) {
            temp[i * 2] = list[i];
            temp[i * 2 + 1] = list[i];
        }

        // The size will be double whatever it was before, set list
        // equal to the new array
        list = temp;
        size *= 2;
    }
	
	
	
    public int size() {
        return this.size; //actual size of the list (not the capacity of array)
    }

    public int get(int index) {
        checkIndex(index); //make sure index is not out of bounds
        return list[index];
    }
    
    //returns first index of occurrence of value in lust, if list doesn't have value returns -1
    public int indexOf(int value) {
        for (int i = 0;i<size;i++) {
            if (list[i] == value) {
                return i;
            }
        }
        return -1;
    }
    
    //checks if the actual list has elements (not the array storing it)
    public boolean isEmpty() {
        return size == 0;
    }
    
    //sets element at index to value
    public void set(int index, int value) {
        //checks the index is not out of bound
        checkIndex(index);
        list[index] = value;
    }

    public void add(int value) {
        //checks if we can add extra element to list (maybe array is out of capacity)
        checkCapacity(size+1);
        list[this.size] = value;//add to the end of the list
        size++;
    }
    public void add(int index, int value) {
        //checks if we can add extra element to list (maybe array is out of capacity)
        checkIndex(index);
        for(int i=size; i>index;i--) {
            list[i]=list[i-1];
        }
        list[index]=value;
        size++;
    }
    public void remove(int index) {
        checkIndex(index);
        for(int i = index;i<size-1;i++) {
            list[i] = list[i+1];
        }
        size--;
    }
    public void checkIndex(int index) {
        //the index has to be between 0 and size -1
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("index: " + index);
        }
    }
    // Checks that array has the given capacity
    // throws an IllegalStateException otherwise
    public void checkCapacity(int capacity) {
        //list.length is actual array list
        if (capacity > list.length) {
            increaseCapacity();
        }
    }
    private void increaseCapacity() {
        //copy original list into new list with doubled capacity (new elements after original lis are 0's)
        list = Arrays.copyOf(list, list.length*2);
    }
    //used to print string [] version of list
    public String toString() {
        if(size == 0) {
            return "[]";
        }
        else {
            String s = "[";
            for (int i = 0; i < size - 1; i++) {
                s = s + list[i] + ",";
            }
            return s + list[size - 1] + "]";
        }
    }
}
