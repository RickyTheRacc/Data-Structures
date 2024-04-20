package com.gradescope.lab11;

/* Header
/ Lab 11
/ Names: Sam Yoder, Nathan Dinh, Johnny Nguyen
*/

public class TreeIntNode {
    public int data;
    public TreeIntNode left;
    public TreeIntNode right;
    public TreeIntNode(int data){
        this.data = data;
        this.left = null;
        this.right = null;
    }
    public TreeIntNode(int data, TreeIntNode left, TreeIntNode right){
        this.data = data;
        this.left = left;
        this.right = right;
    }
}
