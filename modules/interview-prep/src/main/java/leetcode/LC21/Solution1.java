package leetcode.LC21;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;

public class Solution1 extends Solution {

    @Override
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        var a = list1;
        var b = list2;
        var result = new ListNode();
        var tmp = result;

        while (a != null && b != null) {
            if (a.val < b.val) {
                tmp.next = a;
                tmp = a;
                a = a.next;
            } else {
                tmp.next = b;
                tmp = b;
                b = b.next;
            }
        }

        while(a != null) {
            tmp.next = a;
            tmp = a;
            a = a.next;
        }

        while(b != null) {
            tmp.next = b;
            tmp = b;
            b = b.next;
        }

        return result.next;
    }



}
