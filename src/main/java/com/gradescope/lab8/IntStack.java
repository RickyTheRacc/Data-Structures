package com.gradescope.lab8;

/*Header
/Lab 8
/Names: Sam Yoder, Nathan Dinh, Johnny Nguyen
*/

@SuppressWarnings("unused")
public interface IntStack {
    int size();
    int top();
    boolean isEmpty();
    void push(int value);
    int pop();
}
