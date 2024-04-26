package com.gradescope.hw6;

/* Header
/ HW 6
/ Name: Sam Yoder
*/

import java.util.Arrays;
import java.util.NoSuchElementException;

@SuppressWarnings({"unchecked", "unused"})
public class MinHeap<T extends Comparable<T>> {
    private T[] heap;
    private int size;

    public MinHeap(){
        this.heap = (T[]) new Comparable[10];
        this.size = 0;
    }

    //Returns index of left child (exist or doesn't exist - doesn't matter)
    public int getLeftChildIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    // Return index of right child (exist or doesn't exist - doesn't matter)
    public int getRightChildIndex(int parentIndex){
        return 2 * parentIndex + 2;
    }

    // Return index of parent (exist or doesn't exist - doesn't matter)
    public int getParentIndex(int childIndex){
        return (childIndex - 1) / 2;
    }
    //Returns true if left child exists, false otherwise
    public boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) < size;
    }

    // Return true if the right child exists, false otherwise
    public boolean hasRightChild(int index) {
        return getRightChildIndex(index) < size;
    }

    // Return true if the parent exists, false otherwise
    public boolean hasParent(int index) {
        return getParentIndex(index) >= 0;
    }

    // Return element at given index, or throws NoSuchElementException
    public T get(int index){
        if (index >= size) throw new NoSuchElementException();
        return heap[index];
    }

    //Return value of the left child, or throws NoSuchElementException
    public T leftChild(int index) {
        if (!hasLeftChild(index)) throw new NoSuchElementException();
        return heap[getLeftChildIndex(index)];
    }

    //Return value of the right child, or throws NoSuchElementException
    public T rightChild(int index) {
        if (!hasRightChild(index)) throw new NoSuchElementException();
        return heap[getRightChildIndex(index)];
    }

    //Return value of the parent, or throws NoSuchElementException
    public T parent(int index) {
        if (!hasParent(index)) throw new NoSuchElementException();
        return heap[getParentIndex(index)];
    }

    //Return size of the heap (different from capacity)
    public int size(){
        return size;
    }

    //Return true if the heap is empty, false otherwise
    public boolean isEmpty() {
        return size == 0;
    }

    //Write method swap that takes to indexes and exchanges elements at those indexes
    public void swap(int i1, int i2) {
        T temp = heap[i1];
        heap[i1] = heap[i2];
        heap[i2] = temp;
    }

    /* Part "Given"
    * Do not change next three methods.
    * /

    /* This method if called will resize array when it gets full.
    * The size will be doubled.
     */
    public void ensureExtraCapacity() {
        if (size == heap.length) {
            heap = Arrays.copyOf(heap, heap.length*2);
        }
    }

    // This is toString method that prints heap
    public String toString(){
        if (size <= 0) return "[]";

        StringBuilder s = new StringBuilder("[" + heap[0]);

        for (int i = 1; i < size; i++) {
            s.append(",").append(heap[i]);
        }

        return s + "]";
    }

    //This will print the whole array (not just the heap portion)
    public void printHeap(){
        for (T t : heap) System.out.println(t);
    }

    // Creates the heap from a given array, only used in testing part 1
    public void setHeap(T[] arr){
        System.arraycopy(arr, 0, heap, 0, arr.length);
		size = arr.length;
    }

    /*Part 2
    * In this part write: swap, heapifyUp and heapifyDown, peek, add, remove.
    * Make sure to check by using relevant tests.
    * */

    /* Returns min elements of the heap, and throws
    * NoSuchElementException if heap is empty.
    * Note: Do not remove min element!
    */

    public T peek() {
        if (isEmpty()) throw new NoSuchElementException();
        return heap[0];
    }

    // Rebalance the tree starting from last node in the heap
    public void heapifyUp() {
        /*add your code here*/
    }

    // Adds an item to the heap
    public void add(T item) {
        ensureExtraCapacity();


    }

    /* This method should remove min element from heap
    * If heap is empty, throw NoSuchElementException
    * Note: Do you need to heapify? Up or Down?
    */

    public T remove() {
        if (isEmpty()) throw new NoSuchElementException();

        size -= 1;

        return null;
    }

    // Rebalance the tree starting from root node in the heap.
    public void heapifyDown() {
        /*add your code here*/
    }
}
