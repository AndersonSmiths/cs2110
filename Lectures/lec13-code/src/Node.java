import java.util.NoSuchElementException;

class Node<T> {

    /**
     * The data in this node.
     */
    private T data;

    /**
     * The node after this node.  Null if there is no next node.
     */
    private Node<T> next;

    /**
     * Create a new node with data `data` and successor `next` (may be null).
     */
    Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }

    /**
     * Return the data in this node.
     */
    T data() {
        return data;
    }

    /**
     * Return this node's successor (null if none).
     */
    Node<T> next() {
        return next;
    }

    /**
     * Set this node's successor to `next` (may be null).
     */
    void setNext(Node<T> next) {
        this.next = next;
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lecture 12
    ////////////////////////////////////////////////////////////////////////////

    /**
     * Return the position (1-based) of the first occurrence of `target` in the list starting at
     * `head`. Throws NoSuchElementException if `target` is not in the list.
     */
    static <T> int positionOf(Node<T> head, T target) throws NoSuchElementException {
        // Base case: What if head's data matches the target?
        if (head.data.equals(target)) {
            return 1;
        }

        // Base case: What if head's remainder list is empty?
        if (head.next == null) {
            throw new NoSuchElementException();
        }

        // Recursive case: The answer is 1 + "the position of the target in the remainder list"
        return 1 + positionOf(head.next(), target);
    }

    /**
     * Return the position (1-based) of the first occurrence of `target` in the chain of nodes with
     * this node as the head.  Throws NoSuchElementException if `target` is not in this chain.
     */
    int positionOf(T target) throws NoSuchElementException {
        // Implementation constraint: must be recursive
        // Base case: What if head's data matches the target?
        if (this.data.equals(target)) {
            return 1;
        }

        // Base case: What if head's remainder list is empty?
        if (this.next == null) {
            throw new NoSuchElementException();
        }

        // Recursive case: The answer is 1 + "the position of the target in the remainder list"
        return 1 + next.positionOf(target);
    }
}