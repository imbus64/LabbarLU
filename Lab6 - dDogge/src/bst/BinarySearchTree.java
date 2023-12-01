package bst;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class BinarySearchTree<E> {
	BinaryNode<E> root; // Also used in BSTVisualizer
	int size; // Also used in BSTVisualizer
	private Comparator<E> comparator;
	private ArrayList<E> list;

	/**
	 * Constructs an empty binary search tree.
	 */
	public BinarySearchTree() {

	}

	/**
	 * Constructs an empty binary search tree, sorted according to the specified
	 * comparator.
	 *
	 * @param comparator the comparator to determine the order of elements.
	 */
	public BinarySearchTree(Comparator<E> comparator) {
		this.comparator = comparator;
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 *
	 * @param x element to be inserted
	 * @return true if the element was inserted, false if it already exists
	 */
	public boolean add(E x) {
		// Call the recursive add method starting from the root
		if (contains(root, x)) {
			return false;
		}

		root = addRecursive(root, x);
		return true; // Assuming the method always adds the element, you may need to modify this
						// based on your actual implementation
	}

	/**
	 * Private recursive method to add the specified element in the tree.
	 *
	 * @param node the root of the current subtree
	 * @param x    element to be inserted
	 * @return the updated subtree after insertion
	 */
	private BinaryNode<E> addRecursive(BinaryNode<E> node, E x) {
		if (node == null) {
			size++; // Increment size since a new element is added
			return new BinaryNode<>(x);
		}

		// Assuming that E is comparable
		Comparable<? super E> comparableX = (Comparable<? super E>) x;
		int compareResult = comparableX.compareTo(node.element);

		if (compareResult < 0) {
			// Insert to the left subtree
			node.left = addRecursive(node.left, x);
		} else if (compareResult > 0) {
			// Insert to the right subtree
			node.right = addRecursive(node.right, x);
		}
		// If compareResult is 0, the element is a duplicate and not added

		return node;
	}

	/**
	 * Private recursive method to check if the specified element exists in the
	 * tree.
	 *
	 * @param node the root of the current subtree
	 * @param x    element to be checked
	 * @return true if the element exists, false otherwise
	 */
	private boolean contains(BinaryNode<E> node, E x) {
		if (node == null) {
			return false; // Element not found
		}

		Comparable<? super E> comparableX = (Comparable<? super E>) x;
		int compareResult = comparableX.compareTo(node.element);

		if (compareResult < 0) {
			return contains(node.left, x);
		} else if (compareResult > 0) {
			return contains(node.right, x);
		} else {
			return true; // Element found (it's a duplicate)
		}
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
	private int height(BinaryNode<E> node) {
		if (node == null) {
			return 0;
		} else {
			// Calculate the height of left and right subtrees
			int leftHeight = height(node.left);
			int rightHeight = height(node.right);

			// Return the maximum height plus one
			return 1 + Math.max(leftHeight, rightHeight);
		}
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

	// Private recursive method for inorder traversal
	private void inorderTraversal(BinaryNode<E> node) {
		if (node != null) {
			// Traverse left subtree
			inorderTraversal(node.left);

			// Process current node
			System.out.print(node.element + " ");

			// Traverse right subtree
			inorderTraversal(node.right);
		}
	}

	/**
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {
		toArray(root, list);
		root = buildTree(list, 0, list.size() - 1);
	}

	/*
	 * Adds all elements from the tree rooted at 'root' in inorder to the list
	 * sorted.
	 */
	private void toArray(BinaryNode<E> root, List<E> sorted) {
		if (root != null) {
			toArray(root.left, list);
			list.add(root.element);
			toArray(root.right, list);
		}
	}

	/*
	 * Builds a complete tree from the elements from position first to last in the
	 * list sorted. Elements in the list a are assumed to be in ascending order.
	 * Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(ArrayList<E> sorted, int first, int last) {
		if (first > last)
			return null;

		int mid = (first + last) / 2;

		BinaryNode<E> temp = new BinaryNode<>(sorted.get(mid));

		temp.left = buildTree(sorted, first, mid - 1);
		temp.right = buildTree(sorted, mid + 1, last);

		return temp;
	}

	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}
	}

	public static void main(String[] args) {
		BSTVisualizer visualizer = new BSTVisualizer("D7", 500, 500);
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		Random rand = new Random();
		
		tree.add(rand.nextInt(100)+1);
		tree.add(rand.nextInt(100)+1);
		tree.add(rand.nextInt(100)+1);
		tree.add(rand.nextInt(100)+1);
		tree.add(rand.nextInt(100)+1);
		tree.add(rand.nextInt(100)+1);
		tree.add(rand.nextInt(100)+1);
		tree.add(rand.nextInt(100)+1);
		tree.add(rand.nextInt(100)+1);
		tree.add(rand.nextInt(100)+1);
		tree.add(rand.nextInt(100)+1);
		tree.add(rand.nextInt(100)+1);
		tree.add(rand.nextInt(100)+1);
		tree.add(rand.nextInt(100)+1);
		tree.add(rand.nextInt(100)+1);
		tree.add(rand.nextInt(100)+1);
		tree.add(rand.nextInt(100)+1);
		tree.add(rand.nextInt(100)+1);
		tree.add(rand.nextInt(100)+1);
		tree.add(rand.nextInt(100)+1);
		
		visualizer.drawTree(tree);
//		BinarySearchTree<Integer> skewedTree = createSkewedTree();
//		BinarySearchTree<Integer> balancedTree = createBalancedTree();
//
//		System.out.println("Skewed Tree:");
//		skewedTree.printTree();
//		drawTree(skewedTree);
//
//		System.out.println("\nBalanced Tree:");
//		balancedTree.printTree();
//		drawTree(balancedTree);
	}

	private static BinarySearchTree<Integer> createSkewedTree() {
		BinarySearchTree<Integer> skewedTree = new BinarySearchTree<>();
		skewedTree.add(5);
		skewedTree.add(4);
		skewedTree.add(3);
		skewedTree.add(2);
		skewedTree.add(1);
		skewedTree.add(45);
		skewedTree.add(21);
		return skewedTree;
	}

	private static BinarySearchTree<Integer> createBalancedTree() {
		BinarySearchTree<Integer> balancedTree = new BinarySearchTree<>();
		balancedTree.add(3);
		balancedTree.add(1);
		balancedTree.add(5);
		balancedTree.add(2);
		balancedTree.add(4);
		balancedTree.add(6);
		balancedTree.add(45);
		balancedTree.add(21);
		return balancedTree;
	}

	private static void drawTree(BinarySearchTree<?> bst) {
		BSTVisualizer visualizer = new BSTVisualizer("Binary Search Tree Visualizer", 800, 600);
		visualizer.drawTree(bst);
	}

}