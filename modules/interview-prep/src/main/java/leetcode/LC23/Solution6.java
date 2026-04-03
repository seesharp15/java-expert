package leetcode.LC23;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution6 extends Solution {

/*
* You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
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

* */


    @Override
    public ListNode mergeKLists(ListNode[] lists) {

        if (lists == null) return null;
        var q = new PriorityQueue<ListNode>(Comparator.comparingInt(n -> n.val));

        for(var node: lists) {
            if (node != null) q.offer(node);
        }

        var result = new ListNode();
        var tmp = result;

        while(!q.isEmpty()) {

            var current = q.poll();

            if (current.next != null) {
                q.offer(current.next);
            }

            tmp.next = current;
            tmp = current;
        }

        return result.next;


    }
}
