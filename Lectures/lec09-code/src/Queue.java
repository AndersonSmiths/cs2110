public interface Queue<T> {

    /**
     * Whether the queue is empty.
     */
    boolean isEmpty();

    /**
     * Enqueue a new item at the end of the queue.
     */
    void enqueue(T newItem);

    /**
     * Dequeue the item at the front of the queue, and return that item. Throws `EmptyException` if
     * the queue is empty.
     */
    T dequeue();

    /**
     * Return the item on the front of the queue, without modifying the queue. Throws
     * `EmptyException` if the queue is empty.
     */
    T peek();
}