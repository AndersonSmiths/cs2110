package cs2110;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A list of elements of type `T` implemented as a singly linked list.  Null elements are not
 * allowed.
 */
public class LinkedSeq<T> implements Seq<T> {

    /**
     * Number of elements in the list.  Equal to the number of linked nodes reachable from `head`.
     */
    private int size;

    /**
     * First node of the linked list (null if list is empty).
     */
    private Node<T> head;

    /**
     * Last node of the linked list starting at `head` (null if list is empty).  Next node must be
     * null.
     */
    private Node<T> tail;

    /**
     * Assert that this object satisfies its class invariants.
     */
    private void assertInv() {
        assert size >= 0;
        if (size == 0) {
            assert head == null;
            assert tail == null;
        } else {
            assert head != null;
            assert tail != null;


            // checking that number of linked nodes is equal to the lists size
            // code heavily based off of frequencyOf() in linkedlist class
            int numNodes = 0;
            Node<T> currNode = head;
            Node<T> lastNode = head;
            while (currNode != null) {
                numNodes++;
                lastNode = currNode;
                currNode = currNode.next();
            }

            // last node should be the tail at this point
            assert lastNode.equals(tail);
        }

    }

    /**
     * Create an empty list.
     */
    public LinkedSeq() {
        size = 0;
        head = null;
        tail = null;

        assertInv();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void prepend(T elem) {
        assertInv();
        assert elem != null;

        head = new Node<>(elem, head);
        // If list was empty, assign tail as well
        if (tail == null) {
            tail = head;
        }
        size += 1;

        assertInv();
    }

    /**
     * Return a text representation of this list with the following format: the string starts with
     * '[' and ends with ']'.  In between are the string representations of each element, in
     * sequence order, separated by ", ".
     * <p>
     * Example: a list containing 4 7 8 in that order would be represented by "[4, 7, 8]".
     * <p>
     * Example: a list containing two empty strings would be represented by "[, ]".
     * <p>
     * The string representations of elements may contain the characters '[', ',', and ']'; these
     * are not treated specially.
     */
    @Override
    public String toString() {
        String str = "[";

        Node<T> currNode = head;
        while (currNode != null) {
            str += currNode.data();
            // if statement for correct comma syntax
            if (currNode.next() != null) {
                str += ", ";
            }
            currNode = currNode.next();
        }
        str += "]";
        return str;
    }

    @Override
    public boolean contains(T elem) {
        // specification assertion
        assert elem != null;

        // iteration through all nodes to see if elem is within
        Node<T> currNode = head;
        while (currNode != null) {
            if (currNode.data().equals(elem)) {
                return true;
            }
            currNode = currNode.next();
        }

        return false;

    }

    @Override
    public T get(int index) {
        // specification assertion
        assert index >= 0 && index < size;

        // loop to reach index
        int i = 0;
        Node<T> currNode = head;
        while (i != index) {
            currNode = currNode.next();
            i++;
        }

        // .data because of return type T
        return currNode.data();
    }

    @Override
    public void append(T elem) {
        assertInv();
        assert elem != null;

        // if list was empty, add head
        if (size == 0) {
            head = new Node<>(elem, head);
            tail = head;
            size++;
        }

        else {
            // assign new element as tail and change previous tails address to new node
            Node<T> newNode = new Node<T>(elem, null);
            tail.setNext(newNode);

            // reassign tail
            tail = newNode;
            size++;
        }

    }

    @Override
    public void insertBefore(T elem, T successor) {
        assert contains(successor) && successor != null;
        assert elem != null;

        Node<T> currNode = head;
        int index = 0;

        // new head case
        if (head.data().equals(successor)) {
            head = new Node<>(elem, head);
            size++;
        }

        else {

            // loop to find index of successor
            while (!currNode.data().equals(successor)) {
                index += 1;
                currNode = currNode.next();
            }

            // traversing to find node 1 spots before successor
            int count = 0;
            Node<T> curNode = head;

            while (count != index - 1) {
                curNode = curNode.next();
                count++;
            }

            // set this nodes successor to be new element
            Node<T> newNode = new Node<T>(elem, currNode);
            curNode.setNext(newNode);
            size++;
        }

    }

    @Override
    public boolean remove(T elem) {

        assert elem != null;

        if(size == 0 || !(contains(elem))) {
            return false;
        }


        // find elem and its index in the list
        int index = 0;
        Node<T> currNode = head;
        while (!currNode.data().equals(elem)) {
            index++;
            currNode = currNode.next();
        }

        // traversing to find node 1 spots before successor
        int count = 0;
        Node<T> curNode = head;

        while (count < index) {
            curNode = curNode.next();
            count++;
        }


        // edge cases
        if (size ==1) {
            head = null;
            tail = null;
            return true;
        }
        else if (elem.equals(head.data())) {
            head = head.next();
            assertInv();
            return true;
        }
        else if (index == size) {
            curNode.setNext(null);
            tail = curNode;
            assertInv();
            return true;
        }
        else {
            // before and after nodes
            Node<T> beforeNode = curNode;
            Node<T> afterNode = currNode.next();
            Node<T> removeNode = beforeNode.next();
            beforeNode.setNext(afterNode);
            size--;
            assertInv();
            return true;
        }

    }

    /**
     * Return whether this and `other` are `LinkedSeq`s containing the same elements in the same
     * order.  Two elements `e1` and `e2` are "the same" if `e1.equals(e2)`.  Note that `LinkedSeq`
     * is mutable, so equivalence between two objects may change over time.  See `Object.equals()`
     * for additional guarantees.
     */
    @Override
    public boolean equals(Object other) {
        // Note: In the `instanceof` check, we write `LinkedSeq` instead of `LinkedSeq<T>` because
        // of a limitation inherent in Java generics: it is not possible to check at run-time
        // what the specific type `T` is.  So instead we check a weaker property, namely,
        // that `other` is some (unknown) instantiation of `LinkedSeq`.  As a result, the static
        // type returned by `currNodeOther.data()` is `Object`.

        if (!(other instanceof LinkedSeq)) {
            return false;
        }

        LinkedSeq otherSeq = (LinkedSeq) other;
        if (this.size != otherSeq.size()) {
            return false;
        }

        Node<T> currNodeThis = head;
        Node currNodeOther = otherSeq.head;


        while (currNodeThis != null && currNodeOther != null) {
            if (!currNodeThis.data().equals(currNodeOther.data())) {
                return false;
            }
            currNodeThis = currNodeThis.next();
            currNodeOther = currNodeOther.next();
        }
        return true;
    }

    /*
     * There is no need to read the remainder of this file for the purpose of completing the
     * assignment.  We have not yet covered the implementation of these concepts in class.
     */

    /**
     * Returns a hash code value for the object.  See `Object.hashCode()` for additional
     * guarantees.
     */
    @Override
    public int hashCode() {
        // Whenever overriding `equals()`, must also override `hashCode()` to be consistent.
        // This hash recipe is recommended in _Effective Java_ (Joshua Bloch, 2008).
        int hash = 1;
        for (T e : this) {
            hash = 31 * hash + e.hashCode();
        }
        return hash;
    }

    /**
     * Return an iterator over the elements of this list (in sequence order).  By implementing
     * `Iterable`, clients can use Java's "enhanced for-loops" to iterate over the elements of the
     * list.  Requires that the list not be mutated while the iterator is in use.
     */
    @Override
    public Iterator<T> iterator() {
        assertInv();

        // Return an instance of an anonymous inner class implementing the Iterator interface.
        // For convenience, this uses Java features that have not eyt been introduced in the course.
        return new Iterator<>() {
            private Node<T> next = head;

            public T next() throws NoSuchElementException {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T result = next.data();
                next = next.next();
                return result;
            }

            public boolean hasNext() {
                return next != null;
            }
        };
    }
}
