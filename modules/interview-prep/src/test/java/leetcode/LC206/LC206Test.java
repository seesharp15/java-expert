package leetcode.LC206;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LC206Test {

    private Solution getSolution() {
        return new Solution1(); // swap to try other implementations
    }

    @Test
    void returnsNullForEmptyList() {
        assertNull(getSolution().reverseList(null));
    }

    @Test
    void returnsSameNodeForSingleElement() {
        var head = node(7);
        var result = getSolution().reverseList(head);
        assertArrayEquals(new int[]{7}, toArray(result));
        assertNull(result.next);
    }

    @Test
    void reversesTwoNodes() {
        var head = list(1, 2);
        var result = getSolution().reverseList(head);
        assertArrayEquals(new int[]{2, 1}, toArray(result));
    }

    @Test
    void reversesMultipleNodes() {
        var head = list(1, 2, 3, 4, 5);
        var result = getSolution().reverseList(head);
        assertArrayEquals(new int[]{5, 4, 3, 2, 1}, toArray(result));
    }

    @Test
    void maintainsNextPointersCorrectly() {
        var head = list(10, 20, 30);
        var result = getSolution().reverseList(head);

        assertEquals(30, result.val);
        assertEquals(20, result.next.val);
        assertEquals(10, result.next.next.val);
        assertNull(result.next.next.next);
    }

    private static ListNode node(int val) {
        return new ListNode(val);
    }

    private static ListNode list(int... values) {
        if (values.length == 0) return null;
        var head = new ListNode(values[0]);
        var cur = head;
        for (int i = 1; i < values.length; i++) {
            cur.next = new ListNode(values[i]);
            cur = cur.next;
        }
        return head;
    }

    private static int[] toArray(ListNode head) {
        int len = 0;
        for (var n = head; n != null; n = n.next) len++;

        var out = new int[len];
        var cur = head;
        for (int i = 0; i < len; i++) {
            out[i] = cur.val;
            cur = cur.next;
        }
        return out;
    }
}
