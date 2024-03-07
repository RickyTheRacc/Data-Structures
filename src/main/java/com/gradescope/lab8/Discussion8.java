package com.gradescope.lab8;

/*Header
/Lab 8
/Names: Sam Yoder, Nathan Dinh, Johnny Nguyen
*/

public class Discussion8 {
    public static void main(String[] args) {
        LinkedIntStack s = new LinkedIntStack();
        LinkedIntQueue q = new LinkedIntQueue();
        LinkedIntQueue r = new LinkedIntQueue();

        s.push(1);
        s.push(-2);
        s.push(-3);
        s.push(4);
        s.push(5);
        splitStack(s);
        System.out.println(s);

        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(2);
        q.enqueue(1);
        System.out.println(isPalindrome(q));

        r.enqueue(1);
        r.enqueue(2);
        r.enqueue(3);
        r.enqueue(4);
        r.enqueue(5);
        System.out.println(isPalindrome(r));
    }

    public static void splitStack(LinkedIntStack s) {
        LinkedIntQueue queue = new LinkedIntQueue();
        // Size gets updated in the loop so we save it before each time
        int size = s.size(); // Store the size of the stack

        // First we add all the numbers to the queue
        for (int i = 0; i < size; i++) {
            queue.enqueue(s.pop());
        }

        size = queue.size(); // Store the size of the queue
        // Then we add all the negative numbers to the stack
        for (int i = 0; i < size; i++) {
            int temp = queue.dequeue();

            if (temp < 0) s.push(temp);
            else queue.enqueue(temp);
        }

        // Then we add all the positive numbers back to the stack
        size = queue.size(); // Store the size of the queue
        for (int i = 0; i < size; i++) {
            s.push(queue.dequeue());
        }
    }

    public static boolean isPalindrome(LinkedIntQueue q) {
        LinkedIntStack s = new LinkedIntStack();
        int size = q.size(); // Store the size of the queue

        // Add all the numbers to the stack
        for (int i = 0; i < size; i++) {
            int temp = q.dequeue();
            s.push(temp);
            q.enqueue(temp);
        }

        // The stack is now the reverse of the queue
        // We can compare the queue and the stack to see if they are the same
        for (int i = 0; i < size; i++) {
            // If any of the elements don't match, return false
            if (q.dequeue() != s.pop()) return false;
        }

        return true;
    }
}