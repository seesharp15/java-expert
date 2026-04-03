package leetcode.LC23;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
*

Code

Testcase
Testcase

Test Result
23. Merge k Sorted Lists
Hard

Topics
premium lock icon
Companies
You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.

Merge all the linked-lists into one sorted linked-list and return it.



Example 1:

Input: lists = [[1,4,5],[1,3,4],[2,6]]
Output: [1,1,2,3,4,4,5,6]
Explanation: The linked-lists are:
[
  1->4->5,
  1->3->4,
  2->6
]
merging them into one sorted linked list:
1->1->2->3->4->4->5->6
Example 2:

Input: lists = []
Output: []
Example 3:

Input: lists = [[]]
Output: []


Constraints:

k == lists.length
0 <= k <= 104
0 <= lists[i].length <= 500
-104 <= lists[i][j] <= 104
lists[i] is sorted in ascending order.
The sum of lists[i].length will not exceed 104.
* */
public class Solution7 extends Solution {

    @Override
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        var q = new PriorityQueue<ListNode>(Comparator.comparingInt(node -> node.val));

        for(var node: lists){
            if(node != null) q.offer(node);
        }

        var result = new ListNode();
        var current = result;

        while(!q.isEmpty()){
            var node = q.poll();
            current.next = node;
            current = node;

            if (node.next != null){
                q.offer(node.next);
            }
        }

        return result.next;
    }
}
