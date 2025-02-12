/**
 * A set of distinct comparable values.  `T`'s `compareTo()` must be consistent with `equals()`.
 */
public class BstComparableSet<T extends Comparable<T>> {
    /**
     * The root of the binary search tree containing the values in the set.
     * null if set is empty.
     */
    private BstComparableNode<T> root;

    /**
     * Add value `elem` to the set.  If `elem` is already in the set, the set is not changed.
     */
    public void add(T elem) {
        if (root == null) {
            root = new BstComparableNode<>(elem);
        } else {
            root.insert(elem);
        }
    }

    /**
     * Return whether this set contains value `target`.
     */
    public boolean contains(T target) {
        if (root == null) {
            return false;
        } else {
            return root.contains(target);
        }
    }

    /**
     * Return the number of distinct values in this set.
     */
    public int size() {
        if (root == null) {
            return 0;
        } else {
            return root.size();
        }
    }
}