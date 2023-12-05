package labs;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;
import java.util.Random;

import org.junit.jupiter.api.Test;

import bst.BinarySearchTree;

class BinarySearchTreeTest {
    @Test
    void testRebuilding() {
        for (int i = 0; i < 10; i++) {
            BinarySearchTree<Integer> bst = new BinarySearchTree<>();
            for (int j = 0; j < 50; j++) {
                bst.add(j);
            }

            // Rebuild the tree
            bst.rebuild();
            assertTrue(bst.isBalanced());
            assertEquals(bst.size(), 50);
        }
    }

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
        bst.add("banana");
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

    @Test
    void stressTest() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        Random rand = new Random();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 50; j++) {
                bst.add(rand.nextInt(100) + 1);
            }
            assert(bst.size() > 0);
            bst.clear();
            assertEquals(0, bst.size());
        }
    }
}