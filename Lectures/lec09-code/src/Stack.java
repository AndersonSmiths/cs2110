public interface Stack<T> {

    /**
     * Whether the stack is empty.
     */
    boolean isEmpty();

    /**
     * Push a new item on the top of the stack.
     */
    void push(T newItem);

    /**
     * Pop an item off the top of the stack, and return that item. Throws `EmptyException` if the
     * stack is empty.
     */
    T pop();

    /**
     * Return the item on the top of the stack, without modifying the stack. Throws `EmptyException`
     * if the stack is empty.
     */
    T peek();
}