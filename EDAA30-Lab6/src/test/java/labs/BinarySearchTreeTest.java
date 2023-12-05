package labs;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

import bst.BinarySearchTree;

class BinarySearchTreeTest {

    @Test
    void testAdd() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        assertTrue(bst.add(5));
        assertTrue(bst.add(3));
        assertFalse(bst.add(5)); // Adding a duplicate should return false
        assertTrue(bst.add(8));
    }

    @Test
    void testHeight() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        assertEquals(0, bst.height());

        bst.add(5);
        assertEquals(1, bst.height());

        bst.add(3);
        assertEquals(2, bst.height());

        bst.add(8);
        assertEquals(2, bst.height());

        bst.add(2);
        assertEquals(3, bst.height());
    }

    @Test
    void testSize() {
        BinarySearchTree<String> bst = new BinarySearchTree<>();
        assertEquals(0, bst.size());

        bst.add("apple");
        assertEquals(1, bst.size());

        bst.add("banana");
        assertEquals(2, bst.size());

        bst.add("cherry");
        assertEquals(3, bst.size());
    }

    @Test
    void testClear() {
        BinarySearchTree<Double> bst = new BinarySearchTree<>();
        bst.add(3.14);
        bst.add(2.71);
        assertEquals(2, bst.size());

        bst.clear();
        assertEquals(0, bst.size());
        assertNull(bst.root); // Assuming root is public or has a getter method
    }

    @Test
    void testConstructors() {
        // Test default constructor
        BinarySearchTree<Integer> bst1 = new BinarySearchTree<>();
        assertEquals(0, bst1.size());

        // Test constructor with comparator
        Comparator<Integer> reverseComparator = Comparator.reverseOrder();
        BinarySearchTree<Integer> bst2 = new BinarySearchTree<>(reverseComparator);
        bst2.add(5);
        bst2.add(3);
        assertTrue(bst2.add(8)); // Uses reverse order comparator
        assertEquals(3, bst2.size());
    }

}
