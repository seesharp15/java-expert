package leetcode.LC21;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution2 extends Solution {

    /*
    * You are given the heads of two sorted linked lists list1 and list2.

Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.

Return the head of the merged linked list.



Example 1:


Input: list1 = [1,2,4], list2 = [1,3,4]
Output: [1,1,2,3,4,4]
Example 2:

Input: list1 = [], list2 = []
Output: []
Example 3:

Input: list1 = [], list2 = [0]
Output: [0]
*
*
Constraints:

The number of nodes in both lists is in the range [0, 50].
-100 <= Node.val <= 100
Both list1 and list2 are sorted in non-decreasing order.
    * */
    @Override
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        if (list1 == null && list2 == null) return null;

        var q = new PriorityQueue<ListNode>(Comparator.comparingInt(n -> n.val));
        if (list1 != null) q.offer(list1);
        if (list2 != null) q.offer(list2);

        var result = new ListNode();
        var tmp = result;


        while (!q.isEmpty()) {
            var next = q.poll();

            tmp.next = next;
            tmp = next;
            if (next.next != null){
                q.offer(next.next);
            }
        }

        return result.next;

    }
}
