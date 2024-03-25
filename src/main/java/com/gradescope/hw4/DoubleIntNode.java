package com.gradescope.hw4;

public class DoubleIntNode {
    // Instance variables/fields to match picture in directions
    public int data;
    public DoubleIntNode prev;
    public DoubleIntNode next;

    // Default constructor
    public DoubleIntNode(int data){
        this.prev = null;
        this.data = data;
        this.next = null;
    }

    // Second constructor
    public DoubleIntNode(DoubleIntNode prev, int data, DoubleIntNode next) {
        this.prev = prev;
        this.data = data;
        this.next = next;
    }
}