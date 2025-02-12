/**
 * A list is an ordered sequence of non-null items.
 */
public interface List<T> {

    /**
     * Whether the list is empty.
     */
    boolean isEmpty();

    /**
     * The number of items in the list.
     */
    int size();

    /**
     * Add a new item at the beginning of the list. Its position will be 1.
     */
    void addFront(T newItem);

    /**
     * Add a new item at the back of the list. Its position will be `size() + 1`.
     */
    void addBack(T newItem);

    /**
     * Add a new item at the requested position in the list. Throws `IndexOutOfBoundsException`
     * unless `1 <= position <= size() + 1`.
     */
    void addAt(int atPosition, T newItem);

    /**
     * Return the item at the requested position. Throws `IndexOutOfBoundsException` unless `1 <=
     * position <= size()`.
     */
    T get(int position);

    /**
     * Replace the element at the requested position with `newItem`. Throws
     * `IndexOutOfBoundsException` unless `1 <= position <= size()`.
     */
    void set(int position, T newItem);

    /**
     * Remove and return the item at the requested position. Throws `IndexOutOfBoundsException`
     * unless `1 <= * position <= size()`.
     */
    T remove(int atPosition);

    /**
     * Whether any items in this list are duplicates according to `.equals()`.
     */
    boolean hasDuplicates();

    /**
     * The number of copies of `anItem` this bag contains. Might be zero.
     */
    int frequencyOf(T anItem);
}