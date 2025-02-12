package cs2110;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedSeqTest {

    // Helper functions for creating lists used by multiple tests.  By constructing strings with
    // `new`, more likely to catch inadvertent use of `==` instead of `.equals()`.

    /**
     * Creates [].
     */
    static Seq<String> makeList0() {
        return new LinkedSeq<>();
    }

    /**
     * Creates ["A"].  Only uses prepend.
     */
    static Seq<String> makeList1() {
        Seq<String> ans = new LinkedSeq<>();
        ans.prepend(new String("A"));
        return ans;
    }

    /**
     * Creates ["A", "B"].  Only uses prepend.
     */
    static Seq<String> makeList2() {
        Seq<String> ans = new LinkedSeq<>();
        ans.prepend(new String("B"));
        ans.prepend(new String("A"));
        return ans;
    }

    /**
     * Creates ["A", "B", "C"].  Only uses prepend.
     */
    static Seq<String> makeList3() {
        Seq<String> ans = new LinkedSeq<>();
        ans.prepend(new String("C"));
        ans.prepend(new String("B"));
        ans.prepend(new String("A"));
        return ans;
    }

    /**
     * Creates a list containing the same elements (in the same order) as array `elements`.  Only
     * uses prepend.
     */
    static <T> Seq<T> makeList(T[] elements) {
        Seq<T> ans = new LinkedSeq<>();
        for (int i = elements.length; i > 0; i--) {
            ans.prepend(elements[i - 1]);
        }
        return ans;
    }

    @DisplayName("WHEN a LinkedSeq is first constructed, THEN it should be empty.")
    @Test
    void testConstructorSize() {
        Seq<String> list = new LinkedSeq<>();
        assertEquals(0, list.size());
    }

    @DisplayName("GIVEN a LinkedSeq, WHEN an element is prepended, " +
            "THEN its size should increase by 1 each time.")
    @Test
    void testPrependSize() {
        // Note: List creation helper functions use prepend.
        Seq<String> list;

        // WHEN an element is prepended to an empty list
        list = makeList1();
        assertEquals(1, list.size());

        // WHEN an element is prepended to a list whose head and tail are the same
        list = makeList2();
        assertEquals(2, list.size());

        // WHEN an element is prepended to a list with no nodes between its head and tail
        list = makeList3();
        assertEquals(3, list.size());
    }

    @DisplayName("GIVEN a LinkedSeq containing a sequence of values, " +
            "THEN its string representation should include the string representations of its " +
            "values, in order, separated by a comma and space, all enclosed in square brackets.")
    @Test
    void testToString() {
        Seq<String> list;

        // WHEN empty
        list = makeList0();
        assertEquals("[]", list.toString());

        // WHEN head and tail are the same
        list = makeList1();
        assertEquals("[A]", list.toString());

        // WHEN there are no nodes between head and tail
        list = makeList2();
        assertEquals("[A, B]", list.toString());

        // WHEN there are at least 3 nodes
        list = makeList3();
        assertEquals("[A, B, C]", list.toString());

        // WHEN values are not strings
        Seq<Integer> intList = makeList(new Integer[]{1, 2, 3, 4});
        assertEquals("[1, 2, 3, 4]", intList.toString());
    }

    @DisplayName("WHEN the function contains(elem) is called, " +
            "THEN, the function should return true if elem exists in the linkedseq." +
            "IF it is not in the linkedseq, return false. ")
    @Test
    void testContains() {
        Seq<String> lst;
        lst = makeList0();

        // WHEN lst contains 1 instance of elem
        lst.prepend("A");
        lst.prepend("B");
        lst.prepend("C");
        assertEquals(true, lst.contains("B"));

        // WHEN lst contains no instance of elem
        lst.prepend("D");
        lst.prepend("E");
        lst.prepend("F");
        assertEquals(false, lst.contains("Y"));

        // WHEN lst contains more than 1 instance of elem
        lst.prepend("H");
        lst.prepend("I");
        lst.prepend("J");
        lst.prepend("J");
        lst.prepend("K");
        assertEquals(true, lst.contains("J"));

    }

    @DisplayName("GIVEN an number representing an index in the linkedseq" +
            "THEN get(index) should return the node at said index.")
    @Test
    void testGet() {
        Seq<String> lst;
        lst = makeList0();

        // length of 10
        lst.prepend("A");
        lst.prepend("B");
        lst.prepend("C");
        lst.prepend("D");
        lst.prepend("E");
        lst.prepend("F");
        lst.prepend("H");
        lst.prepend("I");
        lst.prepend("J");
        lst.prepend("K");

        // edge cases
        assertEquals("A", lst.get(9));
        assertEquals("K", lst.get(0));

        // middle cases
        assertEquals("F", lst.get(4));
        assertEquals("E", lst.get(5));

    }

    @DisplayName("WHEN this method is called," +
            "THEN it should add an element to the END of a list.")
    @Test
    void testAppend() {
        Seq<String> lst;
        lst = makeList0();

        // empty list
        lst.append("A");
        assertEquals("[A]", lst.toString());

        // non-empty list
        lst.append("B");
        assertEquals("[A, B]", lst.toString());

        // further test
        lst.append("C");
        assertEquals("[A, B, C]", lst.toString());

    }

    @DisplayName("WHEN this method is called, " +
            "THEN an element should be added to the linkedseq right before the input successor")
    @Test
    void testInsertBefore() {
        Seq<String> lst;
        lst = makeList0();

        // length of 10
        lst.append("A");
        lst.append("B");
        lst.append("C");
        lst.append("D");
        lst.append("E");
        lst.append("F");
        lst.append("H");
        lst.append("I");
        lst.append("J");
        lst.append("K");

        // adding J before D
        lst.insertBefore("J", "D");
        assertEquals("J", lst.get(3));

        // adding before current head
        lst.insertBefore("L", "A");
        assertEquals("L", lst.get(0));

        // adding Z before last element of lst
        lst.insertBefore("Z", "K");
        assertEquals("Z", lst.get(11));

    }

    @DisplayName("GIVEN an element, " +
            "THEN this method should remove the node containing said element from the LinkedSeq")
    @Test
    void testRemove() {
        Seq<String> lst;
        lst = makeList0();

        // length of 10
        lst.append("A");
        lst.append("B");
        lst.append("C");
        lst.append("D");
        lst.append("E");
        lst.append("F");
        lst.append("H");
        lst.append("I");
        lst.append("J");
        lst.append("K");

        // basic case
        assertEquals(true, lst.remove("D"));
        assertEquals(9, lst.size());

        // DNE case
        assertEquals(false, lst.remove("Z"));

        // edge case
        assertEquals(true, lst.remove("A"));
        assertEquals(true, lst.remove("K"));

        // empty list
        Seq<String> list;
        list = makeList0();
        assertEquals(false, list.remove("A"));

    }

    @DisplayName("GIVEN another LinkedSeq, " +
            "THEN this method should return true if this LinkedSeq is equal to the input LinkedSeq")
    @Test
    void testEquals() {
        Seq<String> lst;
        lst = makeList0();

        // length of 10
        lst.append("A");
        lst.append("B");
        lst.append("C");
        lst.append("D");
        lst.append("E");
        lst.append("F");
        lst.append("H");
        lst.append("I");
        lst.append("J");
        lst.append("K");

        LinkedSeq<String> set = new LinkedSeq<>();
        set.prepend("a");
        set.prepend("b");
        set.prepend("c");
        LinkedSeq<String> set2 = new LinkedSeq<>();
        set2.prepend("a");
        set2.prepend("b");
        set2.prepend("c");
        assertEquals(true, set.equals(set2));


        // true case
        Seq<String> list;
        Seq<String> lit = makeList0();

        lst.append("A");
        lst.append("B");
        lst.append("C");
        lst.append("D");
        lst.append("E");
        lst.append("F");
        lst.append("H");
        lst.append("I");
        lst.append("J");
        lst.append("K");

        //assertEquals(true, lst.equals(list));

        // same prefix but not equal
        Seq<String> list2;
        list2 = makeList0();

        list2.append("A");
        list2.append("B");
        list2.append("C");
        list2.append("D");
        list2.append("E");
        list2.append("F");
        list2.append("H");
        list2.append("I");
        list2.append("J");
        list2.append("K");
        list2.append("L");
        list2.append("M");
        list2.append("N");

        assertEquals(false, lst.equals(list2));

        // two empty lists
        Seq<String> list3;
        Seq<String> list4;
        list3 = makeList0();
        list4 = makeList0();
        assertEquals(true, list3.equals(list4));

    }


    /*
     * There is no need to read the remainder of this file for the purpose of completing the
     * assignment.  We have not yet covered `hashCode()` or `assertThrows()` in class.
     */

    @DisplayName("GIVEN two distinct LinkedSeqs containing equivalent values in the same order, " +
            "THEN their hash codes should be the same.")
    @Test
    void testHashCode() {
        // WHEN empty
        assertEquals(makeList0().hashCode(), makeList0().hashCode());

        // WHEN head and tail are the same
        assertEquals(makeList1().hashCode(), makeList1().hashCode());

        // WHEN there are no nodes between head and tail
        assertEquals(makeList2().hashCode(), makeList2().hashCode());

        // WHEN there are at least 3 nodes
        assertEquals(makeList3().hashCode(), makeList3().hashCode());
    }

    @DisplayName("GIVEN a LinkedSeq, THEN its iterator should yield its values in order " +
            "AND it should stop yielding after the last value.")
    @Test
    void testIterator() {
        Seq<String> list;
        Iterator<String> it;

        // WHEN empty
        list = makeList0();
        it = list.iterator();
        assertFalse(it.hasNext());
        Iterator<String> itAlias = it;
        assertThrows(NoSuchElementException.class, () -> itAlias.next());

        // WHEN head and tail are the same
        list = makeList1();
        it = list.iterator();
        assertTrue(it.hasNext());
        assertEquals("A", it.next());
        assertFalse(it.hasNext());

        // WHEN there are no nodes between head and tail
        list = makeList2();
        it = list.iterator();
        assertTrue(it.hasNext());
        assertEquals("A", it.next());
        assertTrue(it.hasNext());
        assertEquals("B", it.next());
        assertFalse(it.hasNext());
    }
}
