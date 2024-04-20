package com.gradescope.lab11;

/* Header
/ Lab 11
/ Names: Sam Yoder, Nathan Dinh, Johnny Nguyen
*/

@SuppressWarnings("unused")
public class BSIntTree {
    public static void main(String[] args) {
        TreeIntNode root = new TreeIntNode(10);
        BSIntTree tree = new BSIntTree(root);
        tree.add(5);
        tree.add(15);
        tree.add(3);
        tree.add(7);
        tree.add(12);
        tree.add(17);
        tree.add(1);
        tree.add(4);
        tree.add(6);
        tree.add(8);
        tree.add(11);
        tree.add(13);
        tree.add(16);
        tree.add(18);
        System.out.println(tree.preorder());
        System.out.println(tree.leafSum());
        System.out.println(tree.maxNode().data);
    }
    
    private TreeIntNode root;
    private int size;

    public BSIntTree(TreeIntNode root) {
        this.root = root;
        this.size = 1;
    }

    /** Problem 1 **/
    // Add a preorder method that returns a string of all elements in preorder
    // separated by space (the string will also end with space for simplicity).

    public String preorder() {
        return preorder(root);

    }

    private String preorder(TreeIntNode node){
        StringBuilder result = new StringBuilder();

        // Base case, only keep recursing if node isn't null
        if (node != null){
            // Add the current node's data to the result before the others
            result.append(node.data + " ");
            // Since we're doing preorder we do the left node then the right node
            result.append(preorder(node.left));
            result.append(preorder(node.right));
        }

        return result.toString();
    }

    /** Problem 2 **/
    // Add method leafSum. The method accepts no parameters and returns an integer
    // representing sum of all values of the leaf nodes.

    public int leafSum() {
        return leafSum(root);
    }

    private int leafSum(TreeIntNode node){
        // If there are no children, return zero
        if (node == null) return 0;
        // Only return the data if the node is a leaf, meaning both its left and right are null
        if (node.left == null && node.right == null) return node.data;
        // Otherwise since we know it has at least one child, we can keep recursing
        return leafSum(node.left) + leafSum(node.right);
    }

    /** Problem 3 **/
    // Add a method maxNode. The method accepts no parameters and returns
    // a TreeIntNode pointing at node with the maximum value.

    public TreeIntNode maxNode() {
        return maxNode(root);
    }

    private TreeIntNode maxNode(TreeIntNode node) {
        // We know the max node is the rightmost node, so we keep going right until we can't anymore
        if (node == null) return null;
        if (node.right == null) return node;
        return maxNode(node.right);
    }

    public void add(int data) {
        this.root = add(root, data);
        size++;
    }

    private TreeIntNode add(TreeIntNode node, int data) {
        if (node == null) node = new TreeIntNode(data);

        else {
            if (node.data > data) {
                node.left = add(node.left, data);
            } else if (node.data < data) {
                node.right = add(node.right, data);
            }
        }

        return node;
    }
}
