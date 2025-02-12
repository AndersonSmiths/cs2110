/**
 * A node in a binary search tree containing a character value.
 * A tree composed of these nodes may not contain duplicate values.
 */
class BstCharNode {
    /**
     * The value contained in this node.
     */
    final char data;

    /**
     * This node's left subtree; null if empty.
     * All values in left subtree must be less than `data`.
     */
    BstCharNode left;

    /**
     * This node's right subtree; null if empty.
     * All values in right subtree must be greater than `data`.
     */
    BstCharNode right;

    /**
     * Create a new node with value `data`, left subtree `left`, and right subtree `right`.
     * Empty subtrees are represented by null.
     * Requires all values in `left` are less than `data`, all values in `right` are greater than
     * `data`.
     */
    BstCharNode(char data, BstCharNode left, BstCharNode right) {
        // This is an incomplete assertion of the precondition.  Should be:
        // assert left.max() < data && right.min() > data;
        assert left == null || left.data < data;
        assert right == null || right.data > data;

        this.data = data;
        this.left = left;
        this.right = right;
    }

    /**
     * Create a new leaf node with value `data`.
     */
    BstCharNode(char data) {
        this(data, null, null);
    }

    /**
     * Return the value in this node.
     */
    char data() {
        return data;
    }

    /**
     * Return this node's left subtree.  All values contained in it will be less than this
     * node's value.  Client must not insert any value in left subtree that is not less than
     * this node's value.
     */
    BstCharNode left() {
        return left;
    }

    /**
     * Return this node's right subtree.  All values contained in it will be greater than this
     * node's value.  Client must not insert any value in right subtree that is not greater than
     * this node's value.
     */
    BstCharNode right() {
        return right;
    }

    /**
     * Replace this node's left subtree with `newLeft`.  Requires all values contained in
     * `newLeft` are less than this node's value.
     */
    void setLeft(BstCharNode newLeft) {
        left = newLeft;
    }

    /**
     * Replace this node's right subtree with `newRIght`.  Requires all values contained in
     * `newRight` are greater than this node's value.
     */
    void setRight(BstCharNode newRight) {
        right = newRight;
    }

    /**
     * Return whether this node has no children.
     */
    boolean isLeaf() {
        return left == null && right == null;
    }

    // Free function
    /**
     * Return whether the tree rooted at `subtree` contains the value `target`.
     */
    static boolean contains(BstCharNode subtree, char target) {
        if (subtree == null) {
            // base case: empty tree
            return false;
        } else if (subtree.data() == target) {
            // base case: we found the target!
            return true;
        } else if (target < subtree.data()) {
            // recursive case: recurse to left subtree
            return contains(subtree.left(), target);
        } else {
            // recursive case: recurse to right subtree
            return contains(subtree.right(), target);
        }
    }

    // Instance method
    /**
     * Return whether the tree rooted at this node contains the value `target`.
     */
    boolean contains(char target) {
        if (data == target) { // base case: we found the target!
            return true;
        }
        if (target < data) {
            // recursive case: search left subtree
            return (left == null) ? false : left.contains(target);
        } else {
            // recursive case: search right subtree
            return (right == null) ? false : right.contains(target);
        }
    }

    /**
     * Insert value `c` into the tree rooted at this node.  Requires `c` is not contained in the
     * tree.
     */
    void insert(char c) {
        assert c != data;

        if (c < data) {
            if (left == null) {
                left = new BstCharNode(c);
            } else {
                left.insert(c);
            }
        } else {
            if (right == null) {
                right = new BstCharNode(c);
            } else {
                right.insert(c);
            }
        }
    }

    /**
     * Return the number of nodes in the tree rooted at this node.
     */
    int size() {
        int leftSize = (left == null) ? 0 : left.size();
        int rightSize = (right == null) ? 0 : right.size();
        return 1 + leftSize + rightSize;
    }

    /**
     * Return the number of nodes in the longest path from this node to a leaf.  The height of a
     * leaf is 1.
     */
    int height() {
        int leftHeight = (left == null) ? 0 : left.height();
        int rightHeight = (right == null) ? 0 : right.height();
        return 1 + Math.max(leftHeight, rightHeight);
    }

    /**
     * Return the largest value in this tree less than `bound`. Throw NoneFound if all values in
     * tree are at least as big as `bound`.
     */
    char maxBelow(char bound) throws NoneFound {
        // TODO
        throw new UnsupportedOperationException();
    }
}

class NoneFound extends Exception { }