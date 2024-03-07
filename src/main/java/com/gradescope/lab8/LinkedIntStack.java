package com.gradescope.lab8;

/*Header
/Lab 8
/Names: Sam Yoder, Nathan Dinh, Johnny Nguyen
*/

@SuppressWarnings("unused")
public class LinkedIntStack implements IntStack {
    private LinkedIntNode top;
    private int size;
    
    public LinkedIntStack() {
        top = null;
        size = 0;
    }
    
    public int size() {
        return size;
    }

    public int top() {
        return top.data;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void push(int value) {
        if (size == 0) {
            top = new LinkedIntNode(value);
        } else {
            // New node with next as top
            top = new LinkedIntNode(value, top);
        }

        // Increment size
        size++;
    }

    public int pop() {
        int popped = top.data;
        top = top.next;
        size--;

        return popped;
    }

    public String toString() {
        LinkedIntNode temp = top;
        if (size==0) return ("[]");

        StringBuilder ans = new StringBuilder(("[" + top.data));
        for (int i =1; i<size; i++) {
            temp = temp.next;
            ans.append(",").append(temp.data);
        }

        return ans + ("]");
    }
}