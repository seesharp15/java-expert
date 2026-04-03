package leetcode.LC21;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class LC21Test {

    private final Solution solution = new Solution2();

    @Test
    void bothListsNull() {
        ListNode result = solution.mergeTwoLists(null, null);
        assertNull(result);
    }

    @Test
    void firstListNull() {
        ListNode list2 = build(1, 2, 4);

        ListNode result = solution.mergeTwoLists(null, list2);

        assertArrayEquals(new int[] {1, 2, 4}, toArray(result));
    }

    @Test
    void secondListNull() {
        ListNode list1 = build(1, 3, 4);

        ListNode result = solution.mergeTwoLists(list1, null);

        assertArrayEquals(new int[] {1, 3, 4}, toArray(result));
    }

    @Test
    void exampleCase() {
        ListNode list1 = build(1, 2, 4);
        ListNode list2 = build(1, 3, 4);

        ListNode result = solution.mergeTwoLists(list1, list2);

        assertArrayEquals(new int[] {1, 1, 2, 3, 4, 4}, toArray(result));
    }

    @Test
    void singleElementLists() {
        ListNode list1 = build(1);
        ListNode list2 = build(2);

        ListNode result = solution.mergeTwoLists(list1, list2);

        assertArrayEquals(new int[] {1, 2}, toArray(result));
    }

    @Test
    void duplicateValues() {
        ListNode list1 = build(1, 1, 2);
        ListNode list2 = build(1, 3, 3);

        ListNode result = solution.mergeTwoLists(list1, list2);

        assertArrayEquals(new int[] {1, 1, 1, 2, 3, 3}, toArray(result));
    }

    @Test
    void negativeNumbers() {
        ListNode list1 = build(-10, -3, 0, 5);
        ListNode list2 = build(-6, -2, 4);

        ListNode result = solution.mergeTwoLists(list1, list2);

        assertArrayEquals(new int[] {-10, -6, -3, -2, 0, 4, 5}, toArray(result));
    }

    @Test
    void allElementsInFirstSmallerThanSecond() {
        ListNode list1 = build(1, 2, 3);
        ListNode list2 = build(4, 5, 6);

        ListNode result = solution.mergeTwoLists(list1, list2);

        assertArrayEquals(new int[] {1, 2, 3, 4, 5, 6}, toArray(result));
    }

    @Test
    void allElementsInSecondSmallerThanFirst() {
        ListNode list1 = build(4, 5, 6);
        ListNode list2 = build(1, 2, 3);

        ListNode result = solution.mergeTwoLists(list1, list2);

        assertArrayEquals(new int[] {1, 2, 3, 4, 5, 6}, toArray(result));
    }

    @Test
    void unevenLengths() {
        ListNode list1 = build(1, 2, 7, 8, 9);
        ListNode list2 = build(3, 4);

        ListNode result = solution.mergeTwoLists(list1, list2);

        assertArrayEquals(new int[] {1, 2, 3, 4, 7, 8, 9}, toArray(result));
    }

    private ListNode build(int... values) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        for (int value : values) {
            current.next = new ListNode(value);
            current = current.next;
        }

        return dummy.next;
    }

    private int[] toArray(ListNode head) {
        int size = 0;
        ListNode current = head;

        while (current != null) {
            size++;
            current = current.next;
        }

        int[] result = new int[size];
        current = head;
        int i = 0;

        while (current != null) {
            result[i++] = current.val;
            current = current.next;
        }

        return result;
    }

}
