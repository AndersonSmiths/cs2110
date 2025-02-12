package datastructures;

import java.util.*;

/**
 * A min priority queue of distinct elements of type `KeyType` associated with (extrinsic) integer
 * priorities, implemented using a binary heap paired with a hash table.
 */
public class HeapPQueue<KeyType> implements PQueue<KeyType> {

    /**
     * Pairs an element `key` with its associated priority `priority`.
     */
    private record Entry<T>(T key, double priority) {
        // Note: This is equivalent to declaring a static nested class with fields `key` and
        //  `priority` and a corresponding constructor and observers, overriding `equals()` and
        //  `hashCode()` to depend on the fields, and overriding `toString()` to print their values.
        // https://docs.oracle.com/en/java/javase/17/language/records.html
    }

    /**
     * Associates each element in the queue with its index in `heap`.  Satisfies
     * `heap.get(index.get(e)).key().equals(e)` if `e` is an element in the queue. Only maps
     * elements that are in the queue (`index.size() == heap.size()`).
     */
    private final Map<KeyType, Integer> index;

    /**
     * Sequence representing a min-heap of element-priority pairs.  Satisfies
     * `heap.get(i).priority() >= heap.get((i-1)/2).priority()` for all `i` in `[1..heap.size()]`.
     */
    private final ArrayList<Entry<KeyType>> heap;


    /**
     * Assert that our class invariant is satisfied.  Returns true if it is (or if assertions are
     * disabled).
     */
    private boolean checkInvariant() {
        for (int i = 1; i < heap.size(); ++i) {
            int p = (i - 1) / 2;
            assert heap.get(i).priority() >= heap.get(p).priority();
            assert index.get(heap.get(i).key()) == i;
        }
        assert index.size() == heap.size();
        return true;
    }

    /**
     * Constructs an empty queue.
     */
    @SuppressWarnings("unchecked")
    public HeapPQueue() {
        index = new HashMap<>();
        heap = new ArrayList<>();
        assert checkInvariant();
    }

    /**
     * Effect: Add e with priority p to the priority queue.
     * Throw an illegalArgumentException if e is already in the queue.
     */
    @Override
    public void add(KeyType key, double priority) throws IllegalArgumentException {
        if (index.containsKey(key)) {
            throw new IllegalArgumentException("Key is already in the queue");
        }
        Entry<KeyType> e = new Entry<>(key, priority);
        heap.add(e);
        index.put(key, size()-1);
        bubbleUp(size()-1);
        assert checkInvariant();
    }

    /**
     * Return the size of this heap.  This operation takes constant time.
     */
    @Override
    public int size() { // Do not change this method
        return heap.size();
    }

    /**
     * Returns: true iff the priority queue is empty.
     */
    @Override
    public boolean isEmpty() { return heap.isEmpty(); }


    /**
     * Swap the Entries at indices `i` and `j` in `heap`, updating `index` accordingly.  Requires `0
     * <= i,j < heap.size()`.
     */
    private void swap(int i, int j) {
        assert i >= 0 && i < heap.size();
        assert j >= 0 && j < heap.size();
        Entry<KeyType> temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
        index.put(heap.get(i).key(), i);
        index.put(heap.get(j).key(), j);

    }

    /**
     * Returns: the element of the priority queue with the highest priority,
     * without changing the priority queue.
     * Requires: the priority queue is not empty.
     */
    @Override
    public KeyType peek() {
        assert !isEmpty();
        return heap.getFirst().key();
    }

    /**
     * Returns: the element of the priority queue with highest priority,
     * without changing the priority queue.
     * Requires: the priority queue is not empty.
     */
    @Override
    public double peekAtPriority() {
        assert !(size() == 0);
         return heap.getFirst().priority();
    }

    /**
     * Bubble element at index `index` up in heap to its right place.
     * Bubbling up maintains the class invarient by ensuring that all
     * bubbled up nodes end up in a location in which their values are <=
     * that of their children.
     */
    private void bubbleUp(int index) {


        int parent = (index-1) / 2;
        // inv: all children have values >= their parent
        while (index > 0 && heap.get(parent).priority() > heap.get(index).priority()) {
            swap(index, parent);
            index = parent;
            parent = (index - 1) / 2;
        }

    }

    /**
     * Bubble down element at index `index` up in heap to its right place.
     * Bubbling down maintains the class invarient by ensuring that all
     * bubbled down nodes end up in a location in which their values are <=
     * that of their children.
     */
    private void bubbleDown(int index) {
        int c = (index * 2) + 1;

        // continues until properly situated or at end of queue.
        while (c < size()) {
            if (c + 1 < size() && heap.get(c+1).priority() < heap.get(c).priority()) {
                c+=1;
            }

            if (heap.get(index).priority() <= heap.get(c).priority()) {
                return;
            }
            swap(index, c);
            index = c;
            c = (2*index) + 1;
        }
    }


    /**
     * Removes highest priority element from the queue and then returns
     * it. Throws NoSuchElementException if the heap is empty. Maintains
     * class invarient by bubbling down the node replaced at [0].
     */
    @Override
    public KeyType extractMin() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        KeyType min = heap.getFirst().key();
        swap(0, size() - 1);
        heap.remove(size() - 1);
        index.remove(min);
        if (!isEmpty()) {
            bubbleDown(0);
        }

        assert checkInvariant();
        return min;
    }

    /**
     * Change the priority of value v to p.
     * Throw an IllegalArgumentException if v is not in the heap.
     * Expected time: logarithmic.
     * Worst-case time: linear in the size of the heap.
     */
    @Override
    public void changePriority(KeyType key, double priority) {
        assert index.containsKey(key);
        if (!index.containsKey(key)) {
            throw new IllegalArgumentException("Key not found.");
        }

        // previous priority for bubble handling
        double prev = heap.get(index.get(key)).priority();
        heap.set(index.get(key), new Entry<>(key, priority));

        // bubble handling
        if (prev > priority) {
            bubbleUp(index.get(key));
        } else {
            bubbleDown(index.get(key));
        }

    }

    /**
     * Returns an array of all values in the heap. Does not guarantee that the array will be sorted
     * based on priorities.
     */
    @Override
    public KeyType[] values() {
        KeyType[] vals = (KeyType[]) new Object[size()];
        int index = 0;
        for (Entry<KeyType> entry : heap) {
            vals[index] = entry.key();
            index++;
        }
        return vals;
    }

}
