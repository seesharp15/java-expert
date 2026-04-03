package leetcode.LC2;

public class Solution1 extends Solution {

    @Override
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        var dummy = new ListNode(0);
        var cur = dummy;
        var carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            var x = (l1 != null) ? l1.val : 0;
            var y = (l2 != null) ? l2.val : 0;

            var sum = x + y + carry;
            carry = sum / 10;

            cur.next = new ListNode(sum % 10);
            cur = cur.next;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        return dummy.next;
    }
}
