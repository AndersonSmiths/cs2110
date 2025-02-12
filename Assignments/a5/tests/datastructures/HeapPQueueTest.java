package datastructures;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HeapPQueueTest {
    /**
     * Helper method for constructing new, empty PQueues.  Convenient for testing different
     * implementations.
     */
    static PQueue<Integer> makeQueue() {
        return new HeapPQueue<>();
    }

    @DisplayName("WHEN a new HeapPQueue is created, THEN its size will be 0 AND it will be empty")
    @Test
    void testNew() {
        PQueue<Integer> q = makeQueue();

        assertEquals(0, q.size());
        assertTrue(q.isEmpty());
    }

    @DisplayName("GIVEN an empty HeapPQueue, WHEN an element is added, THEN its size will become 1 "
            + "AND it will no longer be empty")
    @Test
    void testAddToEmpty() {
        PQueue<Integer> q = makeQueue();

        q.add(0, 0);
        assertEquals(1, q.size());
        assertFalse(q.isEmpty());
    }

    @DisplayName("GIVEN an PQueue containing an element x, "
            + "WHEN x (with a different priority) is added to the PQueue, "
            + "THEN an IllegalArgumentException should be thrown")
    @Test
    void throwTest() {
        PQueue<Integer> q = new SlowPQueue<>();
        q.add(1,1);
        assertThrows(IllegalArgumentException.class, () -> q.add(1,2));
    }

    @DisplayName("GIVEN a PQueue containing an element x whose priority is not the minimum, "
            + "WHEN x's priority is changed to become the unique minimum, "
            + "THEN the queue's size will not change "
            + "AND peeking the minimum-priority element will return x "
            + "AND peeking the minimum priority will return x's updated priority")
    @Test
    void testChangePriorityReduce() {
        // Make a non-empty queue by adding multiple distinct elements with non-distinct priorities
        PQueue<Integer> q = makeQueue();
        for (int i = 0; i < 20; i += 1) {
            q.add(i, (double) i/2);
        }
        int givenSize = q.size();

        int x = 10;
        int xPri = -1;
        q.changePriority(x, xPri);
        assertEquals(givenSize, q.size());
        assertEquals(x, q.peek());
        assertEquals(xPri, q.peekAtPriority());
    }

    @DisplayName("GIVEN a non-empty PQueue, WHEN an element is removed,"
            + " THEN it size will decrease by 1.  IF its size was 1, THEN it will become empty.")
    @Test
    void testExtractMinSize() {
        int givenSize = 20;
        PQueue<Integer> q = makeQueue();
        for (int i = 0; i < givenSize; i += 1) {
            q.add(i, (double) i/2);
        }

        for (int n = givenSize; n > 0; n -= 1) {
            assertFalse(q.isEmpty());
            q.extractMin();
            assertEquals(n - 1, q.size());
        }
        assertTrue(q.isEmpty());
    }

    // TODO: Add your own test cases here. Make sure to test your code with good
    // coverage. Some example test cases have been provided for you.


    @DisplayName("WHEN add is called, correct bubbling and swapping should occur. ")
    @Test
    void testAdd() {
        PQueue<Integer> q = makeQueue();
        q.add(3, 3);
        q.add(2, 2);
        assertEquals(2, q.peek());
        q.add(1, 1);
        assertEquals(1, q.peek());
        q.add(0, 0);
        assertEquals(0, q.peek());
    }

    @DisplayName("WHEN add is called on an element that already exists," +
            "a IllegalArgumentException should be thrown. ")
    @Test
    void testAddDuplicate() {
        PQueue<Integer> q = makeQueue();
        q.add(3, 3);
        q.add(2, 2);
        q.add(1, 1);
        q.add(0, 0);
        assertThrows(IllegalArgumentException.class, () -> q.add(2, 2));
    }

    @DisplayName("WHEN peekPriority is called, the correct priorities should return. ")
    @Test
    void testPeekPriority() {
        PQueue<Integer> q = makeQueue();
        q.add(3, 3);
        q.add(2, 2);
        assertEquals(2, q.peekAtPriority());
        q.add(1, 1);
        assertEquals(1, q.peekAtPriority());
        q.add(0, 0);
        assertEquals(0, q.peekAtPriority());
    }

    @DisplayName("WHEN changePriority is called, the priorities shoudl properly update. ")
    @Test
    void testChangePriority() {
        PQueue<Integer> q = makeQueue();
        q.add(3, 3);
        q.add(2, 2);
        q.add(1, 1);
        q.add(0, 0);
        q.changePriority(3, -1);
        assertEquals(3, q.peek());
        assertEquals(-1, q.peekAtPriority());
    }

    @DisplayName("WHEN extractMin is called on a queue with size 1, it should then return true" +
            "when isEmpty is called")
    @Test
    void testIsEmpty() {
        PQueue<Integer> q = makeQueue();
        q.add(3, 3);
        q.extractMin();
        assertTrue(q.isEmpty());
    }

}