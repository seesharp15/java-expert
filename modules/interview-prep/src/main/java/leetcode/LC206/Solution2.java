package leetcode.LC206;

public class Solution2 extends Solution {

    @Override
    public ListNode reverseList(ListNode head) {
        return reverse(head, null);
    }

    private ListNode reverse(ListNode node, ListNode prev) {
        if (node == null) return prev;
        var next = node.next;
        node.next = prev;
        return reverse(next, node);
    }
}
