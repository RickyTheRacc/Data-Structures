package com.gradescope.lab8;

/*Header
/Lab 8
/Names: Sam Yoder, Nathan Dinh, Johnny Nguyen
*/

@SuppressWarnings("unused")
public class Discussion8 {
    public static void main(String[] args) {
        LinkedIntQueue queue = new LinkedIntQueue();
        queue.enqueue(10);
        queue.enqueue(62);
        queue.enqueue(9);
        queue.enqueue(61);
        System.out.println(isPalindrom(queue));
        System.out.println(queue);
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

    public static boolean isPalindrom(LinkedIntQueue q) {
        LinkedIntStack s = new LinkedIntStack();
        int size = q.size(); // Store the size of the queue

        // Add all the numbers to the stack, and back to the queue
        for (int i = 0; i < size; i++) {
            int temp = q.dequeue();
            s.push(temp);
            q.enqueue(temp);
        }

        // We now have the queue in the same order that it was
        // And a stack that is the reverse of the queue

        boolean isPalindrome = true;

        // Now we can compare the elements of each
        for (int i = 0; i < size; i++) {
            int temp = q.dequeue();
            if (temp != s.pop()) isPalindrome = false;
            q.enqueue(temp);
        }

        return isPalindrome;
    }
}