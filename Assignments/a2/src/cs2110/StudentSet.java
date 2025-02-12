package cs2110;

/**
 * A mutable set of students.
 */
public class StudentSet {
    // Implementation: the StudentSet is implemented as a resizable array data structure.
    // Implementation constraint: do not use any classes from java.util.
    // Implementation constraint: assert preconditions for all method parameters and assert that the
    //     invariant is satisfied at least at the end of every method that mutates any fields.

    /**
     * Array containing the students in the set.  Elements `store[0..size-1]` contain the distinct
     * students in this set, none of which are null.  All elements in `store[size..]` are null.  The
     * length of `store` is the current capacity of the data structure and is at least 1.  Two
     * students `s1` and `s2` are distinct if `s1.equals(s2)` is false.
     */
    private Student[] store;

    /**
     * The number of distinct students in this set.  Non-negative and no greater than
     * `store.length`.
     */
    int size;

    /**
     * Assert that this object satisfies its class invariants.
     */
    private void assertInv() {
        assert store != null && store.length > 0;
        assert size >= 0 && size <= store.length;

        for (int i = 0; i < size; ++i) {
            // Check that elements in use are non-null
            assert store[i] != null;

            // Check that students are all distinct
            for (int j = i + 1; j < size; ++j) {
                assert !store[i].equals(store[j]);
            }
        }

        // Check that unused capacity is all null
        for (int i = size; i < store.length; ++i) {
            assert store[i] == null;
        }
    }

    /**
     * Create an empty set of students.
     */
    public StudentSet() {

        this.size = 0;

        //initiliazation of store with length 15
        this.store = new Student[15];

        // assert that invariants are satisfied
        assertInv();

    }

    /**
     * Return the number of students in this set.
     */
    public int size() {
        assertInv();
        return this.size;
    }

    /**
     * Effect: Add student `s` to the set.  Requires `s` is not already in the set.
     */
    public void add(Student s) {
        // check preconditions
        assert !(contains(s));

        // check if backing array is out of space
        // size can be no greater than store.length
        if (size == store.length){
            resize();
        }

        store[size] = s;
        size ++;



    }
    /**
     * Helper method to add(). Creates a new backing array with twice the
     * capacity and has all elements from the old array copied to it.
     */
    public void resize() {

        // creating new array with double length
        Student[] doubleStore = new Student[store.length * 2];

        // for loop to copy all elements
        for (int i = 0; i < size; i++) {
            doubleStore[i] = store[i];
        }

        // reassign store to same values as doubleStore
        store = doubleStore;


    }

    /**
     * Method used in test class to retrieve length of store
     */
    public int length() {
        return store.length;
    }

    /**
     * Return whether this set contains student `s`.
     */
    public boolean contains(Student s) {
        // for loop that iterates through each student in store
        // to see if s is already in store

        for (int i = 0; i < size(); i++){
            if (store[i].equals(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Effect: If student `s` is in this set, remove `s` from the set and return true. Otherwise,
     * return false.
     */
    public boolean remove(Student s) {
        // if student is in the set, remove and shift
        if (contains(s)) {
            int sIndex = indexOf(s);

            // removal via reassignment
            // Logic: say there is a list [1, 2, 3], if I want to remove 2,
            // I can do so by reassigning 2 to 3 and then at the end setting
            // final value to null so as to not violate assertions
            for (int i = sIndex; i <= size-1; i++) {
                store[i] = store[i+1];
            }

            // adjusting size and setting duplicate final value to null
            store[size-1] = null;
            size -= 1;

            assertInv();
            return true;
        }

        // if not in the set, return false
        return false;
    }

    /**
     * Helper method that determines the index of a student in store.
     * If not in the set, then returns -1.
     */
    public int indexOf(Student s) {
        // for loop to determine index of s
        for (int i = 0; i < size; i++) {
            if (store[i].equals(s)) {
                return i;
            }
        }
        return -1;
    }


    /**
     * Return the String representation of this StudentSet.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (int i = 0; i < size; ++i) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(store[i]);
        }
        sb.append("}");
        return sb.toString();
    }
}
