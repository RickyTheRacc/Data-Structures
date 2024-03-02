package com.gradescope.lab6;

/*Header
/Lab 6
/Names: Sam Yoder, Johnny Nguyen, Nathan Dinh
*/

public class LinkedIntList implements IntList{
    //A "pointer" to first node
    private LinkedIntNode front;
    private int size;
    //constructor
    public LinkedIntList(){
        this.front = null;
        this.size = 0;
    }
	
	/*add method stretch 1*/

    public void stretch1() {
        for (int i = 0; i < size; i++) {
            int data = get(i);
            add(i, data); //add the same element after the current element
            i++; //skip the next element, since we're going by two
        }
    }

	/*add method stretch2*/

    public void stretch2() {
        // Start at the front of the list
        LinkedIntNode temp = front;

        while(temp!=null) {
            // Create a new node with the same data as the current node
            LinkedIntNode newNode = new LinkedIntNode(temp.data);

            // Insert the new node after the current node
            newNode.next = temp.next; // newNode.next is the node after the current node
            temp.next = newNode; // The current node's next is the new node
            temp = newNode.next; // Move to the node after the new node

            size++;
        }
    }

    public int get(int index){
        checkIndex(index);
        LinkedIntNode temp = front;
        for(int i = 0; i<index; i++){
            temp = temp.next;
        }
        return temp.data;
    }
    //this method returns the index of the first element of the list that has desired value, or -1 if not in the list
    public int indexOf(int value){
        LinkedIntNode temp =front;
        int index = 0;
        while(temp!=null && temp.next!=null){
            if(temp.data == value){
                return index;
            }
            temp=temp.next;
            index++;
        }
        return -1;
    }
    public boolean isEmpty(){
        return size==0;
    }
    public void set(int index, int value){
        checkIndex(index);
        LinkedIntNode temp = front;
        for(int i = 0; i<index; i++){
            temp = temp.next;
        }
        temp.data = value;
    }
    public int size(){
        return size;
    }
    public void add(int data){
        LinkedIntNode l = new LinkedIntNode(data);
        LinkedIntNode temp = this.front;
        if (this.front ==null) {
            this.front = l;
        }
        else{
            //front is not null
            while(temp.next != null){
                //there is a next node and it is not null
                temp=temp.next;
            }
            //temp point to the last node
            temp.next =l;
        }
        size++;
    }
    public void add(int index, int data){
        checkIndex(index);
        LinkedIntNode temp = front;
        if(index ==0){
            front = new LinkedIntNode(data,front);
        }
        else {
            for (int i = 0; i < index - 1; i++) {
                temp = temp.next;
            }
            //pointing to a node before the index (insertion point)
            temp.next = new LinkedIntNode(data, temp.next);
        }
        size++;
    }
    //work in progress!
    public void remove(int index){
        //lets assume index is fine
        checkIndex(index);
        if(index==0 && front!=null){ //need to remove very first element
            front = front.next; //redirect front to element 1
        }
        else {
            LinkedIntNode temp = front;
            for (int i = 0; i < index - 1; i++) {
                temp = temp.next;
            }
            //pointing to a node before the index (insertion point)
            temp.next = temp.next.next;
        }
        this.size--;
    }
    public void checkIndex(int index) {
        //index should be between 0 and size-1
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("index: " + index);
        }
    }
    //prints string version of the list [] format
    public String toString(){
        LinkedIntNode temp = front;
        if(size==0){
            return ("[]");
        }
        else{
            String ans = ("[" +front.data);
            for(int i =1;i<size;i++){
                temp =temp.next;
                ans = ans+ (","+temp.data);
            }
            return ans + ("]");
        }
    }
}
