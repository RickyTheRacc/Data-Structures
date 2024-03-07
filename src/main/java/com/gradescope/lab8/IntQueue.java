package com.gradescope.lab8;

/*Header
/Lab 8
/Names: Sam Yoder, Nathan Dinh, Johnny Nguyen
*/

@SuppressWarnings("unused")
interface IntQueue {
    int size();
    int first();
    boolean isEmpty();
    void enqueue(int value);
    int dequeue();
}
