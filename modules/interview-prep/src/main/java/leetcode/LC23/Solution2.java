package leetcode.LC23;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution2 extends Solution {



    public ListNode mergeKLists(ListNode[] lists) {

        var pq = new PriorityQueue<ListNode>(Comparator.comparingInt(n -> n.val));


        for(var head : lists) {
            pq.offer(head);
        }


        var result = new ListNode();
        var current = result;

        while(!pq.isEmpty()) {

            var node = pq.poll();

            current.next = node;
            current = node;

            if (current.next != null) {
                pq.offer(current.next);
            }
        }

        return result.next;

    }


}
