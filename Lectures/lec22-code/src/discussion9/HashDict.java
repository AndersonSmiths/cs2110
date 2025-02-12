package discussion9;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

// Group members
// * Name (NetID)
// * Name (NetID)

/**
 * An entry in a dictionary containing a key of type `K` and its associated value of type `V`.  The
 * entry's value may be changed.
 */
class Entry<K, V> {

    /**
     * This entry's key.  May not be null.
     */
    private final K key;

    /**
     * The value currently associated with this entry's key.
     */
    private V value;

    /**
     * Construct a new entry with key `key` and associated value `value`.
     */
    Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Return this entry's key.
     */
    K key() {
        return key;
    }

    /**
     * Return the value currently associated with this entry's key.
     */
    V value() {
        return value;
    }

    /**
     * Associate `value` with this entry's key, replacing the previously associated value.
     */
    void setValue(V value) {
        this.value = value;
    }
}

/**
 * A dictionary associating values of type `V` with keys of type `K` implemented using a
 * dynamically-sized hash table. Hash collisions are resolved using chaining.  Load factor shall not
 * exceed 75%.
 */
public class HashDict<K, V> {

    /**
     * The hash table: an array of chains of entries.  Chains are represented by `List`s.  A null
     * element represents an empty chain (empty lists are also allowed).
     */
    private List<Entry<K, V>>[] buckets;

    /**
     * The number of entries currently in this dictionary.  Equal to the number of `discussion9.Entry`s
     * reachable from `buckets`.
     */
    private int size;

    /**
     * Construct a new empty dictionary.
     */
    @SuppressWarnings("unchecked")
    public HashDict() {
        // Initial capacity is 4 (arbitrary)
        buckets = (List<Entry<K, V>>[]) new LinkedList<?>[4];
        size = 0;
        assertInv();
    }

    /**
     * Return the number of keys in this dictionary.
     */
    public int size() {
        return size;
    }

    /**
     * Return the current load factor of this dictionary's hash table.  The load factor is the
     * number of entries divided by the length of the buckets array.
     */
    public double loadFactor() {
        return (double) size / buckets.length;
    }

    /**
     * Return the index of the bucket that a key with a hash code of `hashCode` should be stored in
     * for a hash table with `numBuckets` buckets.
     */
    private static int deriveIndex(int hashCode, int numBuckets) {
        return Math.abs(hashCode % numBuckets);
    }

    /**
     * Return the value associated with `key` in this dictionary.  Throws `NoSuchElementException`
     * if `key` is not in this dictionary.
     */
    public V get(K key) {
        assertInv();

        // Compute index of bucket where this key would go
        int i = deriveIndex(key.hashCode(), buckets.length);

        // If bucket is null, key is not in this dictionary
        if (buckets[i] == null) {
            throw new NoSuchElementException();
        }

        // Search for key in the bucket; if found, return corresponding value
        for (Entry<K, V> e : buckets[i]) {
            if (e.key().equals(key)) {
                return e.value();
            }
        }

        // If key was not found, key is not in this dictionary
        throw new NoSuchElementException();
    }

    /**
     * Associate `value` with key `key` in this dictionary.  If a value was previously associated
     * with this key, it is replaced.
     */
    public void put(K key, V value) {
        assertInv();

        // Compute index of bucket where this key would go
        int i = deriveIndex(key.hashCode(), buckets.length);

        if (buckets[i] == null) {
            buckets[i] = new LinkedList<>();
            size++;
        }

        boolean contains = false;
        for (Entry<K, V> e : buckets[i]) {
            if (buckets[i].contains(e)) {
                e.setValue(value);
                contains = true;
            }
        }

        if (!contains) {
            buckets[i].add(new Entry<>(key, value));
            size++;
        }

    }

    /**
     * Replace `buckets` with an array of length `newSize` and move all entries to their appropriate
     * bucket in the new array.
     */
    @SuppressWarnings("unchecked")
    private void resize(int newSize) {
        List<Entry<K, V>>[] newBuckets = (List<Entry<K, V>>[]) new LinkedList<?>[newSize];
        // TODO: Implement according to specifications
        throw new UnsupportedOperationException();
    }

    /**
     * Assert that this dictionary's class invariants are satisfied.
     */
    void assertInv() {
        // Set of all keys encountered while iterating over entries
        java.util.Set<K> keys = new java.util.HashSet<>();

        for (int i = 0; i < buckets.length; ++i) {
            List<Entry<K, V>> bucket = buckets[i];
            if (bucket == null) {
                continue;
            }
            for (Entry<K, V> e : bucket) {
                // Check for duplicate keys
                boolean newKey = keys.add(e.key());
                assert newKey;

                // Check that entry is in correct bucket
                int index = deriveIndex(e.key().hashCode(), buckets.length);
                assert index == i;
            }
        }

        // Check that size matches number of unique keys
        assert keys.size() == size;

        // TODO: Uncomment this after implementing resizing in `put()`
        // assert loadFactor() <= 0.75;
    }
}
