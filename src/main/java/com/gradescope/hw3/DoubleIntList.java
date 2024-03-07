package com.gradescope.hw3;

public class DoubleIntList implements IntList {
    // First and last nodes of the list
    public DoubleIntNode front, end;
    // How many nodes are in the list
    public int size;

    // Default constructor
    public DoubleIntList() {
        front = null;
        size = 0;
        end = null;
    }

    // Returns the size of the list
    public int size() {
        return size;
    }

    // Returns the first node of the list
    public DoubleIntNode getFront() {
        return front;
    }

    // Returns the last node of the list
    public DoubleIntNode getEnd() {
        return end;
    }

    /*===== Part 2 =====*/

    // Should be used in add and remove methods to check if the index is valid
    public void checkIndex(int index) {
        // Index should always be between 0 and size - 1
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index: " + index + "and size: " + size);
        }
    }

    // Adds a new node to the end of the list
    public void add(int value) {
        // Since we're not adding at a specific index, we don't need to check the index

        // If the list is empty, the new node is both the front and end
        if (size == 0) {
            front = new DoubleIntNode(value);
            end = front;
        }
        // If the list is not empty, the new node is added to the end
        else {
            DoubleIntNode temp = new DoubleIntNode(end, value, null);
            end.next = temp;
            end = temp;
        }

        // Increase the size of the list
        size++;
    }
    
    // Adds a new node at the specified index
    public void add(int index, int value) {
        checkIndex(index);

        // If the index is 0, the new node is added to the front
        if (index == 0) {
            DoubleIntNode temp = new DoubleIntNode(null, value, front);
            front.prev = temp;
            front = temp;
        }
        // If the index is the size, the new node is added to the end
        else if (index == size) {
            DoubleIntNode temp = new DoubleIntNode(end, value, null);
            end.next = temp;
            end = temp;
        }
        // If the index is in the middle, the new node is added in the middle
        else {
            DoubleIntNode current = front;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }

            DoubleIntNode temp = new DoubleIntNode(current.prev, value, current);
            current.prev.next = temp;
            current.prev = temp;
        }

        // Increase the size of the list
        size++;
    }

    /*===== Part 3 =====*/

    // Prints the list to a string in the format [1,2,3]
    public String toString() {
        StringBuilder result = new StringBuilder("[");

        // If the list is empty, return an empty string
        if (size == 0) return result.append("]").toString();

        // If the list is not empty, add the first node to the string
        DoubleIntNode current = front;
        result.append(current.data);

        // Add the rest of the nodes to the string
        for (int i = 0; i < size - 1; i++) {
            current = current.next;
            result.append(",").append(current.data);
        }

        // Return the string
        return result.append("]").toString();
    }

    /*===== Part 4 =====*/

    // Updates the data of the node at the specified index
    public void set(int index, int value) {
        checkIndex(index);

        // If the index is 0, update the front node
        if (index == 0) front.data = value;

        // If the index is the size - 1, update the end node
        else if (index == size - 1) end.data = value;

        // If the index is in the middle, update the node in the middle
        else {
            DoubleIntNode current = front;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }

            current.data = value;
        }
    }

    // Returns the data of the node at the specified index
    public int get(int index) {
        checkIndex(index);

        // If the index is 0, return the data of the front node
        if (index == 0) return front.data;

        // If the index is the size - 1, return the data of the end node
        if (index == size - 1) return end.data;

        // If the index is in the middle, return the data of the node in the middle
        DoubleIntNode current = front;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.data;
    }

    // Returns the index of the first node with the specified value
    public int indexOf(int value) {
        // If the list is empty, return -1
        if (size == 0) return -1;

        // Check every node in the list
        DoubleIntNode current = front;
        for (int i = 0; i < size; i++) {
            if (current.data == value) return i;
            current = current.next;
        }

        // No node with the specified value was found
        return -1;
    }

    // Returns true if the list is empty
    public boolean isEmpty() {
        return size == 0;
    }

    /*===== Part 5 =====*/

    // Removes the node at the specified index
    public void remove(int index) {
        checkIndex(index);

        // If size is one or less, remove the only node
        if (index == 0) {
            if (front.next != null) {
                front = front.next;
                front.prev = null;
            } else {
                front = null;
                end = null;
            }
        }

        // If the index is the size - 1, remove the end node
        else if (index == size - 1) {
            end = end.prev;
            end.next = null;
        }

        // If the index is in the middle, remove the node in the middle
        else {
            DoubleIntNode current = front;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }

            current.prev.next = current.next;
            current.next.prev = current.prev;
        }

        // Decrease the size of the list
        size--;
    }

    /*===== Part 6 =====*/

    // Use selection sort to sort the list in ascending order
    public void sort() {
        // If the list is empty or one element it's already sorted
        if (size <= 1) return;

        // The current node we're sorting
        DoubleIntNode current = front;

        // Loop through the list
        for (int i = 0; i < size - 1; i++) {
            // The node with the smallest value we've found so far
            DoubleIntNode min = current;

            // Loop through the rest of the list
            DoubleIntNode check = current.next;
            for (int j = i + 1; j < size; j++) {
                // If we find a smaller value, set it to min
                if (check.data < min.data) min = check;
                check = check.next;
            }

            // Swap the current node's data with the smaller value
            int temp = current.data;
            current.data = min.data;
            min.data = temp;

            // Move to the next node
            current = current.next;
        }
    }
}
