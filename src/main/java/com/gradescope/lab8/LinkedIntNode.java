package com.gradescope.lab8;

/*Header
/Lab 8
/Names: Sam Yoder, Nathan Dinh, Johnny Nguyen
*/

public class LinkedIntNode {
    public int data;
    public LinkedIntNode next;

    public LinkedIntNode(int data) {
        this.data = data;
        this.next = null;
    }

    public LinkedIntNode(int data, LinkedIntNode next) {
        this.data = data;
        this.next = next;
    }
}
