package bst;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        d7SubRoutine();
        skewedSubRoutine();
        balancedSubRoutine();
    }

    /**
     * Draws the specified tree.
     * 
     * @param bst the BinarySearchTree object to be drawn
     */
    private static void drawTree(BinarySearchTree<?> bst, String title) {
        BSTVisualizer visualizer = new BSTVisualizer("Binary Search Tree Visualizer", 800, 600);
        visualizer.drawTree(bst);
    }

    private static void d7SubRoutine() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        Random rand = new Random();
        BSTVisualizer visualizer = new BSTVisualizer("D7", 1500, 500);

        // Adding random integers to the tree
        for (int i = 0; i < 50; i++) {
            tree.add(rand.nextInt(100) + 1);
        }

        tree.printTree();
        visualizer.drawTree(tree);
    }

    private static void skewedSubRoutine() {
        BinarySearchTree<Integer> skewedTree = createSkewedTree();
        System.out.println("Skewed Tree:");
        skewedTree.printTree();
        drawTree(skewedTree, "Skewed Tree");
    }


    private static void balancedSubRoutine() {
        BinarySearchTree<Integer> balancedTree = createBalancedTree();
        System.out.println("\nBalanced Tree:");
        balancedTree.printTree();
        drawTree(balancedTree, "Balanced Tree");
    }

    // D6-related code
    /**
     * Creates a skewed tree.
     * 
     * @return a BinarySearchTree object
     */
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

    /**
     * Creates a balanced tree.
     * 
     * @return a BinarySearchTree object
     */
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

}
