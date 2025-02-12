import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * A node in a general tree containing a value of type `T`.  No ordering is maintained among its
 * children.
 */
class TreeNode<T> {
    /**
     * The value contained in this node.
     */
    final T data;

    /**
     * This node's children.  Must not contain any ancestor of this node.
     */
    Set<TreeNode<T>> children;

    /**
     * Create a new leaf node with value `data`.
     */
    TreeNode(T data) {
        this.data = data;
        children = new HashSet<>();
    }

    /**
     * Return the value in this node.
     */
    T data() {
        return data;
    }

    /**
     * Return this node's children.
     */
    Iterable<TreeNode<T>> children() {
        return Collections.unmodifiableSet(children);
    }

    /**
     * Add `child` as a child of this node.
     * Requires: `child` is not an ancestor or decendent of this node.
     */
    void addChild(TreeNode<T> child) {
        children.add(child);
    }

    /**
     * Return whether this node has no children.
     */
    boolean isLeaf() {
        return children.isEmpty();
    }

    /**
     * Return the number of nodes in the tree rooted at this node.
     */
    int size() {
        int nodeCount = 1;
        // Note: No distiguished base case, because base case is when `children` is empty.
        for (TreeNode<T> child : children) {
            nodeCount += child.size();
        }
        return nodeCount;
    }

    /**
     * Return the number of nodes in the longest path from this node to a leaf.  The height of a
     * leaf is 1.
     */
    int height() {
        int maxChildHeight = 0;
        // Note: No distiguished base case, because base case is when `children` is empty.
        for (TreeNode<T> child : children) {
            maxChildHeight = Math.max(maxChildHeight, child.height());
        }
        return 1 + maxChildHeight;
    }

    /**
     * Return whether this node or any of its descendants contains a value equal to `target`.
     * Time complexity: O(size)
     * Space complexity: O(height)
     */
    boolean contains(T target) {
        if (data.equals(target)) {
            return true;
        }
        for (TreeNode<T> child : children) {
            if (child.contains(target)) { // only return early if we find a `true` answer
                return true;
            }
        }
        return false;
    }
}