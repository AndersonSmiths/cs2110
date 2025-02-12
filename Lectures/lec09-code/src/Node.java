class Node<T> {

    /**
     * The data in this node.
     */
    private T data;

    /**
     * The node after this node.
     */
    private Node<T> next;

    Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }

    T data() {
        return data;
    }

    void setData(T newData) {
        data = newData;
    }

    Node<T> next() {
        return next;
    }

    void setNext(Node<T> next) {
        this.next = next;
    }
}
