package leetcode.LC21;

import leetcode.LC21.ListNode;

/*You are given the heads of two sorted linked lists list1 and list2.

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


Constraints:

The number of nodes in both lists is in the range [0, 50].
-100 <= Node.val <= 100
Both list1 and list2 are sorted in non-decreasing order.*/

public class Solution {



    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        var root = new ListNode();
        var result = root;

        var current1 = list1;
        var current2 = list2;

        while (current1 != null || current2 != null) {

            if (current1 == null) {
                result.next = current2;
                current2 = current2.next;
            } else if (current2 == null || current1.val <= current2.val) {
                result.next = current1;
                current1 = current1.next;
            } else {
                result.next = current2;
                current2 = current2.next;
            }

            result = result.next;
        }

        return root;
    }
}