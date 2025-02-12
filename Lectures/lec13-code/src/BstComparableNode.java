/**
 * A node in a binary search tree containing a value implementing Comparable.
 * A tree composed of these nodes may not contain duplicate values.
 * `T`'s `compareTo()` must be consistent with `equals()`.
 */
class BstComparableNode<T extends Comparable<T>> { // setting an upper bound on the generic type.
    // Note: a more flexible type parameter would be `T extends Comparable<? super T>`, which uses
    // bounded wildcard type parameters; see DSAJ Java Interlude 5.

    /**
     * The value contained in this node.
     */
    final T data;

    /**
     * This node's left subtree; null if empty.
     * All values in left subtree must be less than `data`.
     */
    BstComparableNode<T> left;

    /**
     * This node's right subtree; null if empty.
     * All values in right subtree must be greater than `data`.
     */
    BstComparableNode<T> right;

    /**
     * Create a new node with value `data`, left subtree `left`, and right subtree `right`.
     * Empty subtrees are represented by null.
     * Requires all values in `left` are less than `data`, all values in `right` are greater than
     * `data`.
     */
    BstComparableNode(T data, BstComparableNode<T> left, BstComparableNode<T> right) {
        // This is an incomplete assertion of the precondition.  Should be:
        // assert left.max() < data && right.min() > data;
        assert left == null || left.data.compareTo(data) < 0;
        assert right == null || right.data.compareTo(data) > 0;

        this.data = data;
        this.left = left;
        this.right = right;
    }

    /**
     * Create a new leaf node with value `data`.
     */
    BstComparableNode(T data) {
        this(data, null, null);
    }

    /**
     * Return the value in this node.
     */
    T data() {
        return data;
    }

    /**
     * Return this node's left subtree.  All values contained in it will be less than this
     * node's value.  Client must not insert any value in left subtree that is not less than
     * this node's value.
     */
    BstComparableNode<T> left() {
        return left;
    }

    /**
     * Return this node's right subtree.  All values contained in it will be greater than this
     * node's value.  Client must not insert any value in right subtree that is not greater than
     * this node's value.
     */
    BstComparableNode<T> right() {
        return right;
    }

    /**
     * Replace this node's left subtree with `newLeft`.  Requires all values contained in
     * `newLeft` are less than this node's value.
     */
    void setLeft(BstComparableNode<T> newLeft) {
        left = newLeft;
    }

    /**
     * Replace this node's right subtree with `newRight`.  Requires all values contained in
     * `newRight` are greater than this node's value.
     */
    void setRight(BstComparableNode<T> newRight) {
        right = newRight;
    }

    /**
     * Return whether this node has no children.
     */
    boolean isLeaf() {
        return left == null && right == null;
    }

    /**
     * Return the node in this tree with data `target` or, if no such node exists, the node that
     * could serve as `target`'s parent.
     */
    BstComparableNode<T> findNode(T target) {
        // TODO: follow along in demo!
        //  We will start by copying over BstCharNode.contains().
        throw new UnsupportedOperationException();
    }

    /**
     * Return whether the tree rooted at this node contains the value `target`.
     */
    boolean contains(T target) {
        return findNode(target).data().equals(target);
    }

    /**
     * Insert value `newData` into the tree rooted at this node.  If `newData` is already in the
     * tree, the tree is not changed.
     */
    void insert(T newData) {
        BstComparableNode<T> parent = findNode(newData);

        if (parent.data().equals(newData)) {
            // New data is already in tree
            return;
        }

        BstComparableNode<T> newNode = new BstComparableNode<>(newData);
        if (newData.compareTo(parent.data()) < 0) {
            // New node belongs on parent's left
            assert parent.left() == null;
            parent.setLeft(newNode);
        } else {
            // New node belongs on parent's right
            assert newData.compareTo(parent.data()) > 0;
            assert parent.right() == null;
            parent.setRight(newNode);
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
}
