import java.util.NoSuchElementException;

/**
 * A max heap of integers.  Element values are priorities.
 */
public class IntMaxHeap {
    /**
     * Array representation of a complete binary tree.  `values[0..size)` satisfies max-heap order
     * invariant.  Length of `values` is capacity of heap.
     */
    int[] values;

    /**
     * Number of elements in the heap.
     */
    int size;

    /** Create an empty heap that can store up to `capacity` elements. */
    public IntMaxHeap(int capacity) {
        values = new int[capacity];
        size = 0;
    }

    /** Return the largest element in this heap. */
    public int peek() {
        if (size == 0) {
            throw new NoSuchElementException("Heap is empty");
        }
        return values[0];
    }

    /** Remove and return the largest element in this heap. */
    public int poll() {
        if (size == 0) {
            throw new NoSuchElementException("Heap is empty");
        }
        int max = values[0];
        values[0] = values[size - 1];
        size -= 1;
        bubbleDown(0);
        return max;
    }

    /** Add `elem` to this heap. */
    public void add(int elem) {
        if (size == values.length) {
            throw new RuntimeException("Heap is full");
        }
        values[size] = elem;
        size += 1;
        bubbleUp(size - 1);
    }

    /**
     * Bubble element at index `k` up in heap to its right place.
     * Precondition: Every `values[i]` â‰¤ its parent except perhaps for `values[k]`.
     */
    private void bubbleUp(int k) {
        // Invariant: The class invariant holds except that the priority
        // . . . . . . of k may be > the priority of k's parent.
        int parent = (k - 1) / 2;
        while (k > 0 && values[k] > values[parent]) {
            swap(k, parent);
            k = parent;
            parent = (k - 1) / 2;
        }
    }

    /**
     * Bubble element at index `k` down in heap to its right place.
     * If the two children have the same priority, bubble down the left one.
     * Precondition: {@code 0 <= k < size} and
     *   Each `values[i]` â‰¥ its children except perhaps for `values[k]`.
     */
    void bubbleDown(int k) {
        // Invariant: The class invariant holds except that the
        // . . . . . . priority of element at `k` may be < the priority of a child.
        int c = 2*k + 1;
        while (c < size) {  // while the left child exists
            // Set `c` to the right child if it exists and its priority is larger
            if (c + 1 < size && values[c + 1] > values[c]) {
                c += 1;
            }

            // If order invariant is locally satisfied, no need to bubble down further
            if (values[k] >= values[c]) {
                return;
            }

            // Swap `k` with larger child, then update `c` to point to new left child
            swap(k, c);
            k = c;
            c = 2*k + 1;
        }
    }

    /** Swap elements at indices `i` and `j`. */
    private void swap(int i, int j) {
        int tmp = values[i];
        values[i] = values[j];
        values[j] = tmp;
    }
}











public void bubbleUp(int k) {
    int parent = (k /2) - 1;
    while (k > 0 && values[parent] < values[k]) {
        swap(parent, k);
        k = parent;
        parent = (k/2) - 1;
    }

public void bubbleDown(int k) {
        int c = (2*k) + 1;

        while (c < size) {
            if (c+1 < size && values[c+1] > values[c]) {
                c+=1;
            }

            if (values[k] > values[c]) {
                return;
            }
            swap(k, c);
            k = c;
            c = 2*k + 1

        }

    }

public void add(int elem) {
        if (size == values.length) {
            throw new NoSuchElementException();
        }
        values[size] = elem;
        size ++;
        bubbleUp(size-1);
    }
public void remove(int k) {
        if (size == 0) {
            throw new RuntimeException();
        }
        int max = values[0];
        values[0] = values[size-1];
        bubbleDown(0);
    }

}


