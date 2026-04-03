package leetcode.LC23;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LC23Test {


    private Solution getSolution() {
        return new Solution7(); // swap to Solution2, etc.
    }

    @Test
    void returnsNullForNullInput() {
        var solution = getSolution();
        solution = getSolution();
        assertNull(solution.mergeKLists(null));
    }

    @Test
    void returnsNullForEmptyArray() {
        var solution = getSolution();
        solution = getSolution();
        ListNode[] lists = new ListNode[0];
        assertNull(solution.mergeKLists(lists));
    }

    @Test
    void returnsNullWhenAllListsAreNull() {
        var solution = getSolution();
        solution = getSolution();
        ListNode[] lists = new ListNode[]{null, null, null};
        assertNull(solution.mergeKLists(lists));
    }

    @Test
    void handlesSingleNullList() {
        var solution = getSolution();
        solution = getSolution();
        ListNode[] lists = new ListNode[]{null};
        assertNull(solution.mergeKLists(lists));
    }

    @Test
    void handlesSingleList() {
        var solution = getSolution();
        solution = getSolution();
        ListNode[] lists = new ListNode[]{
                list(1, 2, 3, 4)
        };

        assertListEquals(
                list(1, 2, 3, 4),
                solution.mergeKLists(lists)
        );
    }

    @Test
    void mergesThreeSortedLists() {
        var solution = getSolution();
        solution = getSolution();
        ListNode[] lists = new ListNode[]{
                list(1, 4, 5),
                list(1, 3, 4),
                list(2, 6)
        };

        assertListEquals(
                list(1, 1, 2, 3, 4, 4, 5, 6),
                solution.mergeKLists(lists)
        );
    }

    @Test
    void handlesMixtureOfNullAndNonNullLists() {
        var solution = getSolution();
        solution = getSolution();
        ListNode[] lists = new ListNode[]{
                null,
                list(1, 3, 5),
                null,
                list(2, 4, 6),
                null
        };

        assertListEquals(
                list(1, 2, 3, 4, 5, 6),
                solution.mergeKLists(lists)
        );
    }

    @Test
    void handlesDuplicateValues() {
        var solution = getSolution();
        solution = getSolution();
        ListNode[] lists = new ListNode[]{
                list(1, 1, 1),
                list(1, 1),
                list(1)
        };

        assertListEquals(
                list(1, 1, 1, 1, 1, 1),
                solution.mergeKLists(lists)
        );
    }

    @Test
    void handlesNegativeNumbers() {
        var solution = getSolution();
        solution = getSolution();
        ListNode[] lists = new ListNode[]{
                list(-10, -5, 0),
                list(-6, -3, 2),
                list(-8, 1, 3)
        };

        assertListEquals(
                list(-10, -8, -6, -5, -3, 0, 1, 2, 3),
                solution.mergeKLists(lists)
        );
    }

    @Test
    void handlesListsOfDifferentLengths() {
        var solution = getSolution();
        solution = getSolution();
        ListNode[] lists = new ListNode[]{
                list(1),
                list(2, 3, 4, 5, 6),
                list(0, 7)
        };

        assertListEquals(
                list(0, 1, 2, 3, 4, 5, 6, 7),
                solution.mergeKLists(lists)
        );
    }

    @Test
    void handlesManySingleElementLists() {
        var solution = getSolution();
        solution = getSolution();
        ListNode[] lists = new ListNode[]{
                list(5),
                list(1),
                list(3),
                list(2),
                list(4)
        };

        assertListEquals(
                list(1, 2, 3, 4, 5),
                solution.mergeKLists(lists)
        );
    }

    @Test
    void handlesAlreadyGloballyOrderedRanges() {
        var solution = getSolution();
        solution = getSolution();
        ListNode[] lists = new ListNode[]{
                list(1, 2, 3),
                list(4, 5, 6),
                list(7, 8, 9)
        };

        assertListEquals(
                list(1, 2, 3, 4, 5, 6, 7, 8, 9),
                solution.mergeKLists(lists)
        );
    }

    @Test
    void handlesInterleavedRanges() {
        var solution = getSolution();
        solution = getSolution();
        ListNode[] lists = new ListNode[]{
                list(1, 4, 7),
                list(2, 5, 8),
                list(3, 6, 9)
        };

        assertListEquals(
                list(1, 2, 3, 4, 5, 6, 7, 8, 9),
                solution.mergeKLists(lists)
        );
    }

    @Test
    void handlesListsContainingIntegerExtremes() {
        var solution = getSolution();
        solution = getSolution();
        ListNode[] lists = new ListNode[]{
                list(Integer.MIN_VALUE, -1, 0),
                list(1, Integer.MAX_VALUE)
        };

        assertListEquals(
                list(Integer.MIN_VALUE, -1, 0, 1, Integer.MAX_VALUE),
                solution.mergeKLists(lists)
        );
    }

    private static ListNode list(int... values) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        for (int value : values) {
            tail.next = new ListNode(value);
            tail = tail.next;
        }

        return dummy.next;
    }

    private static void assertListEquals(ListNode expected, ListNode actual) {
        ListNode e = expected;
        ListNode a = actual;

        while (e != null && a != null) {
            assertEquals(e.val, a.val);
            e = e.next;
            a = a.next;
        }

        assertNull(e, "Expected list has more nodes than actual list");
        assertNull(a, "Actual list has more nodes than expected list");
    }
}

