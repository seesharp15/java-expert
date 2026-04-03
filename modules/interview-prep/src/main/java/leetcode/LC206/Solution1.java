package leetcode.LC206;

public class Solution1 extends Solution {

    @Override
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode prev = null;
        var current = head;

        while (current != null) {
            var next = current.next; // store
            current.next = prev;     // reverse pointer
            prev = current;          // advance prev
            current = next;          // advance cursor
        }

        return prev;
    }
}
