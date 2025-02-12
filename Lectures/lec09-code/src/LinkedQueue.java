public class LinkedQueue<T> implements Queue<T> {
    /**
     * The head of the linked chain of nodes representing this queue. The elements of the queue are
     * the `data` in each node of the chain.
     */
    Node<T> head;

    /**
     * The tail of the linked chain of nodes representing this queue. Invariant: is the last object
     * reached by following all `next()` fields in the chain starting from `head`; is null if and
     * only if `head` is null.
     */
    Node<T> tail;

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void enqueue(T newItem) {
        Node<T> newNode = new Node<>(newItem, null);
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new EmptyException();
        }
        T ans = head.data();
        head = head.next();

        // Check if queue only had one element left
        if (head == null) {
            tail = null;
        }

        return ans;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new EmptyException();
        }
        return head.data();
    }
}