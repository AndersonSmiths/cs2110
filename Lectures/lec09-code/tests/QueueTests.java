import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class QueueTests {

    void testQueue(Queue<Integer> queue) {
        // A new queue should start empty
        assertTrue(queue.isEmpty());

        // A queue should dequeue items in the same order they were enqueued
        final int NUM_ITEMS = 100;
        for (int i = 0; i < NUM_ITEMS; i++) {
            queue.enqueue(i);
        }
        for (int i = 0; i < NUM_ITEMS; i++) {
            assertEquals(i, queue.peek());
            int j = queue.dequeue();
            assertEquals(i, j);
        }

        // After dequeuing all enqueued items, the queue should be empty
        assertTrue(queue.isEmpty());
    }

    @Test
    void testLinkedQueue() {
        // You could also create and test an ArrayStack.
        testQueue(new LinkedQueue<>());
    }
}