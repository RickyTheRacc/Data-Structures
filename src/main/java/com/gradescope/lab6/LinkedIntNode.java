package com.gradescope.lab6;

/*Header
/Lab 6
/Names:
*/

public class LinkedIntNode {
    //the data
    public int data;
    //next node
    public LinkedIntNode next;
    //constructor that only needs node data
    public LinkedIntNode(int data){
        this.data = data;
        this.next = null;
    }
    //constructor needs data and next node
    public LinkedIntNode(int data, LinkedIntNode next){
        this.data = data;
        this.next = next;
    }
}
