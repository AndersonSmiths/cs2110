import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class StackTests {

    void testStack(Stack<Integer> stack) {
        // A new stack should start empty
        assertTrue(stack.isEmpty());

        // A stack should pop items in the reverse order they were pushed
        final int NUM_ITEMS = 100;
        for (int i = 0; i < NUM_ITEMS; i++) {
            stack.push(i);
        }
        for (int i = NUM_ITEMS - 1; i >= 0; i--) {
            assertEquals(i, stack.peek());
            int j = stack.pop();
            assertEquals(i, j);
        }

        // After all pushed values have been popped, a stack should be empty
        assertTrue(stack.isEmpty());
    }

    @Test
    void testLinkedStack() {
        // You could also create and test an ArrayStack.
        testStack(new LinkedStack<>());
    }
}