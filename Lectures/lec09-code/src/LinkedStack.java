public class LinkedStack<T> implements Stack<T> {
    /** Node containing the bottom element of the stack; null if stack is empty. */
    private Node<T> head;

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void push(T newItem) {
        head = new Node<>(newItem, head);
    }

    @Override
    public T pop() {
        if (isEmpty()) {
        throw new EmptyException();

    }



    @Override
    public T peek() {
        if (isEmpty()) {
            throw new EmptyException();
        }
        return head.data();
    }
}