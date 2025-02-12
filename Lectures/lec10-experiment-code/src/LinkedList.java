public class LinkedList<T> implements List<T> {

    /**
     * The head of the linked chain of nodes representing this list. The elements of the list are
     * the `data` in each node of the chain. Invariant: none of the nodes may contain `null` as the
     * data element.
     */
    Node<T> head;

    /**
     * The tail of the linked chain of nodes representing this list. Invariant: is the last object
     * reached by following all `next()` fields in the chain starting from `head`; is null if and
     * only if `head` is null.
     */
    Node<T> tail;

    /**
     * The number of items in the list. Invariant: equals the number of nodes in the chain starting
     * at `head`.
     */
    int size;

    public LinkedList() {
        head = null;
        tail = null;
        size = 0;
        assertInv();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // DISCUSS: all these operations, with lots of commentary, can be found in DSAJ chapter 12.

    @Override
    public void addAt(int atPosition, T newItem) {
        if (atPosition < 1 || atPosition > size + 1) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> newNode = new Node<>(newItem, null);

        // DISCUSS: return to lecture slides for exercise on adding in middle of list.

        if (size == 0) {
            head = newNode;
            tail = newNode;
        } else if (atPosition == 1) {
            newNode.setNext(head);
            head = newNode;
        } else if (atPosition == size + 1) {
            tail.setNext(newNode);
            tail = newNode;
        } else {
            Node<T> nodeBefore = getNodeAt(atPosition - 1);
            Node<T> nodeAfter = nodeBefore.next();
            newNode.setNext(nodeAfter);
            nodeBefore.setNext(newNode);
        }

        size++;
        assertInv();
    }

    /**
     * Return the node at position `atPosition` in the list. Requires `1 <= atPosition <= size`.
     */
    private Node<T> getNodeAt(int atPosition) {
        assert (1 <= atPosition && atPosition <= size);
        Node<T> currNode = head;
        for (int i = 1; i < atPosition; i++) {
            currNode = currNode.next();
        }
        return currNode;
    }

    @Override
    public boolean hasDuplicates() {
        Node<T> nodeA = head;
        while (nodeA != null) {
            Node<T> nodeB = nodeA.next();
            while (nodeB != null) {
                if (nodeA.data().equals(nodeB.data())) {
                    return true;
                }
                nodeB = nodeB.next();
            }
            nodeA = nodeA.next();
        }
        return false;
    }

    @Override
    public int frequencyOf(T anItem) {
        int frequency = 0;
        Node<T> currNode = head;
        while (currNode != null) {
            if (currNode.data().equals(anItem)) {
                frequency++;
            }
            currNode = currNode.next();
        }
        return frequency;
    }


    @Override
    public void addFront(T newItem) {
        addAt(1, newItem);
    }

    @Override
    public void addBack(T newItem) {
        addAt(size + 1, newItem);
    }

    @Override
    public T get(int position) {
        if (position < 1 || position > size) {
            throw new IndexOutOfBoundsException();
        }
        return getNodeAt(position).data();
    }

    @Override
    public void set(int position, T newItem) {
        if (position < 1 || position > size) {
            throw new IndexOutOfBoundsException();
        }
        getNodeAt(position).setData(newItem);
    }

    @Override
    public T remove(int atPosition) {
        if (atPosition < 1 || atPosition > size) {
            throw new IndexOutOfBoundsException();
        }

        T removedItem = null;

        if (size == 1) {
            removedItem = head.data();
            head = null;
            tail = null;
        } else if (atPosition == 1) {
            removedItem = head.data();
            head = head.next();
        } else if (atPosition == size) {
            removedItem = tail.data();
            Node<T> beforeNode = getNodeAt(size - 1);
            beforeNode.setNext(null);
            tail = beforeNode;
        } else {
            Node<T> beforeNode = getNodeAt(atPosition - 1);
            Node<T> removeNode = beforeNode.next();
            Node<T> afterNode = removeNode.next();
            removedItem = removeNode.data();
            beforeNode.setNext(afterNode);
        }

        size--;
        assertInv();

        return removedItem;
    }

    private static boolean checkInv = false;

    private void assertInv() {
        if (!checkInv) {
            return;
        }

        int computedSize = 0;
        Node<T> currNode = head;
        Node<T> prevNode = null;
        while (currNode != null) {
            assert head.data() != null;
            computedSize++;
            assert computedSize <= size;
            prevNode = currNode;
            currNode = currNode.next();
        }
        assert computedSize == size;
        assert prevNode == tail;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<T> currNode = head;
        while (currNode != null) {
            sb.append(currNode.data());
            currNode = currNode.next();
            if (currNode != null) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}