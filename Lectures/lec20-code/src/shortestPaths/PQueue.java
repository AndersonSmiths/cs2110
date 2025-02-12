package shortestPaths;

import java.util.*;

/**
 * A demo implementation of a (min) priority queue whose elements (of type T) are associated with an
 * independent floating-point priority that can be changed.  Note that this implementation's
 * `updatePriority()` method is NOT time-efficient (O(N) cost).
 */
public class PQueue<T> {
    /**
     * A standard library priority queue of "entries" that pair items and their (current)
     * priorities.
     */
    private PriorityQueue<Entry<T>> contents;

    /**
     * Create an empty priority queue.
     */
    public PQueue() {
        contents = new PriorityQueue<>();
    }

    /**
     * Return whether this queue contains no items.
     */
    public boolean isEmpty() {
        return contents.isEmpty();
    }

    /**
     * Return the number of items contained in this queue.
     */
    public int size() {
        return contents.size();
    }

    /**
     * Inserts `item` into this priority queue with priority `priority`.
     */
    public void add(T item, double priority) {
        contents.add(new Entry<>(item, priority));
    }

    /**
     * Retrieves and removes the lowest-priority item in this queue.  Throws NoSuchElementException
     * if this queue is empty.
     */
    public T remove() {
        Entry<T> next = contents.poll();
        if (next == null) {
            throw new NoSuchElementException("Cannot remove from empty queue.");
        }
        return next.item();
    }

    /**
     * Change the priority of `item` to `priority`.  Requires `item` is in this queue.
     */
    public void updatePriority(T item, double priority) {
        // XXX: This is an inefficient implementation for demo purposes only.
        // The `removeIf()` operation is linear in the size of the queue, whereas we desire
        // logarithmic performance for this operation.
        boolean removed = contents.removeIf(e -> e.item.equals(item));
        if (!removed) {
            throw new NoSuchElementException("Cannot change priority of item not in qeue.");
        }
        add(item, priority);
    }

    /**
     * Pairs an item of type `T` with a floating-point priority.  Comparisons between two Entries
     * act on their priorities.
     */
    private static class Entry<T> implements Comparable<Entry<T>> {
        private T item;
        private double priority;

        public Entry(T item, double priority) {
            this.item = item;
            this.priority = priority;
        }

        public T item() {
            return item;
        }

        @Override
        public int compareTo(Entry<T> other) {
            return Double.compare(priority, other.priority);
        }
    }
}
