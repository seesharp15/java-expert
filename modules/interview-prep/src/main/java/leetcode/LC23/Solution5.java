package leetcode.LC23;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution5 extends Solution {
    @Override
    public ListNode mergeKLists(ListNode[] lists) {

        if (lists == null || lists.length == 0) return null;

        var q = new PriorityQueue<ListNode>(Comparator.comparingInt(n -> n.val));

        for(var list: lists){
            if (list != null) {
                q.offer(list);
            }
        }

        var result = new ListNode();
        var tmp = result;

        while(!q.isEmpty()){

            var current = q.poll();
            tmp.next = current;
            tmp = current;

            if (current.next!=null){
                q.offer(current.next);
            }
        }

        return result.next;
    }
}
