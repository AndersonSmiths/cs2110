import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ListTests {

    static final int NUM_ITEMS = 100;

    void testListAdd(List<Integer> list) {
        // Test initial size
        assertEquals(0, list.size());

        // Test addBack and size
        for (int i = 1; i <= NUM_ITEMS; i++) {
            list.addBack(i);
            assertEquals(i, list.size());
        }

        // Test get
        for (int i = 1; i <= NUM_ITEMS; i++) {
            int j = list.get(i);
            assertEquals(i, j);
        }

        // Test addAt
        // This is a tricky test case to write! As tricky as `addAt` itself.
        list.addAt(1, -1); // front
        list.addAt(2, 0); // interior, one-off front
        list.addAt(NUM_ITEMS + 3, NUM_ITEMS + 2); // back
        list.addAt(NUM_ITEMS + 3, NUM_ITEMS + 1); // interior, one-off back
        for (int i = 1; i <= NUM_ITEMS + 4; i++) {
            int j = list.get(i);
            assertEquals(i - 2, j);
        }
    }

    void testListRemove(List<Integer> list) {
        // Test adding items at back, then removing every item from front.
        for (int i = 1; i <= NUM_ITEMS; i++) {
            list.addBack(i);
        }
        for (int i = 1; i <= NUM_ITEMS; i++) {
            int j = list.remove(1);
            assertEquals(i, j);
        }
        assertEquals(0, list.size());

        // Test adding items at front, then removing every item from back.
        for (int i = 1; i <= NUM_ITEMS; i++) {
            list.addFront(i);
        }
        for (int i = 1; i <= NUM_ITEMS; i++) {
            int j = list.remove(list.size());
            assertEquals(i, j);
        }
        assertEquals(0, list.size());

        // Test removing from middle.
        for (int i = 1; i <= NUM_ITEMS; i++) {
            list.addBack(i);
        }
        int middleValue = 2;
        while (list.size() > 2) {
            int j = list.remove(2);
            assertEquals(middleValue, j);
            middleValue++;
        }
        assertEquals(2, list.size());
    }

    void testHasDuplicates(List<Integer> list) {
        // No duplicates when empty
        assertFalse(list.hasDuplicates());

        // No duplicates when singleton
        list.addBack(1);
        assertFalse(list.hasDuplicates());

        // No duplicates after many insertions
        for (int i = 2; i < NUM_ITEMS; i++) {
            list.addBack(i);
        }
        assertFalse(list.hasDuplicates());

        // But now a duplicate
        list.addBack(1);
        assertTrue(list.hasDuplicates());
    }

    @Test
    void testLists() {
        testListAdd(new LinkedList<>());
        testListRemove(new LinkedList<>());
        testListAdd(new LinkedList<>());
        testListRemove(new LinkedList<>());
        testHasDuplicates(new LinkedList<>());
        testHasDuplicates(new LinkedList<>());
    }
}