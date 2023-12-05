package bst;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BinarySearchTree<T> {
    public BinaryNode<T> root = null; // Root node of the binary search tree
    private Comparator<T> comparator = null;
    private int size = 0; // Number of elements in the tree

    /**
     * Constructs an empty binary search tree.
     */
    public BinarySearchTree() {
        this((Comparator<T>) Comparator.naturalOrder());// Some seriously slippery stuff
    }

    /**
     * Constructs an empty binary search tree, sorted according to the specified
     * comparator.
     *
     * @param comparator the comparator to determine the order of elements.
     */
    public BinarySearchTree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    /**
     * Static nested class for the nodes of the binary search tree.
     */
    static class BinaryNode<T> {
        T element;
        BinaryNode<T> left;
        BinaryNode<T> right;

        private BinaryNode(T element) {
            this.element = element;
        }
    }

    /**
     * Inserts the specified element in the tree if no duplicate exists.
     *
     * @param data element to be inserted
     * @return true if the element was inserted, false if it already exists
     */
    public boolean add(T data) {
        if (contains(root, data))
            return false;

        root = addRecursive(root, data);
        return true;
    }

    /**
     * Private recursive method to add the specified element in the tree.
     *
     * @param node the root of the current subtree
     * @param data element to be inserted
     * @return the updated subtree after insertion
     */
    private BinaryNode<T> addRecursive(BinaryNode<T> node, T data) {
        if (node == null) {
            size++;
            return new BinaryNode<>(data);
        }

        int comparison = comparator.compare(data, node.element);
        if (comparison == 0)
            return null; // Already exists

        if (comparison < 0)
            node.left = addRecursive(node.left, data);
        else
            node.right = addRecursive(node.right, data);

        return node;
    }

    /**
     * Private recursive method to check if the specified element exists in the
     * tree.
     *
     * @param node the root of the current subtree
     * @param data element to be checked
     * @return true if the element exists, false otherwise
     */
    private boolean contains(BinaryNode<T> node, T data) {
        if (node == null)
            return false;

        int comparison = comparator.compare(data, node.element);
        if (comparison == 0)
            return true;

        if (comparison < 0)
            return contains(node.left, data);
        else
            return contains(node.right, data);
    }

    /**
     * Computes the height of the tree.
     *
     * @return the height of the tree
     */
    public int height() {
        return height(root);
    }

    /**
     * Private recursive method to compute the height of the tree rooted at the
     * specified node.
     *
     * @param node the root of the current subtree
     * @return the height of the subtree rooted at the specified node
     */
    private int height(BinaryNode<T> node) {
        if (node == null)
            return 0;

        // Recursively compute the height of the left and right subtrees
        return 1 + Math.max(height(node.left), height(node.right));
    }

    /**
     * Returns the number of elements in this tree.
     *
     * @return the number of elements in this tree
     */
    public int size() {
        return size;
    }

    /**
     * Removes all of the elements from this tree.
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Print tree contents in inorder.
     */
    public void printTree() {
        inorderTraversal(root);
    }

    /**
     * Private recursive method for inorder traversal.
     * 
     * @param node the root of the current subtree
     */
    private void inorderTraversal(BinaryNode<T> node) {
        if (node == null)
            return;

        inorderTraversal(node.left);
        System.out.print(node.element + " ");
        inorderTraversal(node.right);
    }

    /**
     * Builds a complete tree from the elements in the tree.
     */
    public void rebuild() {
        ArrayList<T> list = new ArrayList<>(this.size); // Temporary list to store elements
        toArray(root, list); // List is passed by reference, and toArray actually doesent return anything
        root = buildTree(list, 0, list.size() - 1);
    }

    /**
     * Adds all elements from the tree rooted at 'root' in inorder to the list
     * sorted.
     * 
     * @param root   the root of the current subtree
     * @param sorted the list to store the elements
     */
    private void toArray(BinaryNode<T> root, List<T> sorted) { // Signature as in the lab instructions
        if (root == null)
            return;

        toArray(root.left, sorted); // Traverse left subtree
        sorted.add(root.element); // Add current node to the sorted list
        toArray(root.right, sorted); // Traverse right subtree
    }

    /**
     * Builds a complete tree from the elements from position first to last in the
     * list sorted. Elements in the list a are assumed to be in ascending order.
     * Returns the root of tree.
     * 
     * Note that first and last are not iterators, but rather indexes.
     * 
     * @param sorted the list storing the elements
     * @param first  the starting index of the sublist
     * @param last   the ending index of the sublist
     */
    private BinaryNode<T> buildTree(ArrayList<T> sorted, int first, int last) {
        // Base case: If the starting index is greater than the ending index, the
        // sublist is empty
        if (first > last)
            return null; // Return null as there are no elements in the sublist

        // Calculate the middle index of the sublist
        int mid_idx = (first + last) / 2;

        // Create a new node with the element at the middle index as the root of the
        // current subtree
        BinaryNode<T> new_node = new BinaryNode<>(sorted.get(mid_idx));

        // Recursively build the left and right subtrees
        new_node.left = buildTree(sorted, first, mid_idx - 1);
        new_node.right = buildTree(sorted, mid_idx + 1, last);

        // Return the root of the current subtree
        return new_node;
    }

    /**
     * Checks if the tree is balanced.
     * 
     * @return true if the tree is balanced, false otherwise
     */
    public boolean isBalanced() {
        return isBalanced(root);
    }

    /**
     * Private recursive method to check if the tree rooted at the specified node is
     * balanced.
     * 
     * @param node the root of the current subtree
     * @return true if the tree is balanced, false otherwise
     */
    private boolean isBalanced(BinaryNode<T> node) {
        if (node == null)
            return true;

        // eq is not actually eq here, but is descriptive enough
        boolean height_delta_eq = Math.abs(height(node.left) - height(node.right)) <= 1;

        if (height_delta_eq && isBalanced(node.left) && isBalanced(node.right))
            return true;

        return false;
    }
}