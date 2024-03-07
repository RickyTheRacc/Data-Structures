package com.gradescope.lab8;

/*Header
/Lab 8
/Names: Sam Yoder, Nathan Dinh, Johnny Nguyen
*/

@SuppressWarnings("unused")
public class LinkedIntQueue implements IntQueue {
    private LinkedIntNode first;
    private LinkedIntNode last;
    private int size;
    
    public LinkedIntQueue() {
        first = null;
        last = null;
        size = 0;
    }
    
    public int size() {
        return size;
    }

    public int first() {
        return first.data;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void enqueue(int value) {
        LinkedIntNode newNode = new LinkedIntNode(value);

        if (size == 0) {
            // Set first and last to the new node and make them reference each other
            first = newNode;
            last = newNode;
            first.next = last;
        } else {
            last.next = newNode;
            last = last.next;
        }

        // Increment size
        size++;
    }

    public int dequeue() {
        int dequeued = first.data; // store the data of the first node
        first = first.next; // move first to the next node

        // Decrement size inside the if statement
        if (--size == 0) last = null;
        return dequeued;
    }

    public String toString() {
        if (size == 0) return ("[]");
        LinkedIntNode temp = first;

        StringBuilder ans = new StringBuilder(("[" + first.data));

        for (int i = 1; i < size; i++) {
            temp = temp.next;
            ans.append(",").append(temp.data);
        }

        return ans + ("]");
    }
}
