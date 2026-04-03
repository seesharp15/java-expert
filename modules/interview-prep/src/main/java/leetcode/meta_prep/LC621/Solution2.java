package leetcode.meta_prep.LC621;

import java.util.HashMap;

public class Solution2 extends Solution {

    @Override
    public int leastInterval(char[] tasks, int n) {





        var map = new HashMap<Character, Integer>();

        //var largest = 0;

        //var greater = 0;

        for(var task: tasks) {
            map.compute(task, (x, y) -> y == null ? 1 : y + 1);
            //largest = Math.max(total, largest);
        }

        var totalGreater = 0;
        var totalLessthan = 0;
        var lessthan = 0;
        for(var task: tasks) {
            var entry = map.get(task);
            if (entry > n) {
                var v = entry;
                //greater++;
                totalGreater += v;

            } else {
                lessthan++;
                totalLessthan += entry;
            }
        }


        var expectedRowSize = n + 1;
        var totalExpectedTasks = (expectedRowSize * lessthan) - n;


        var ans = totalExpectedTasks + totalGreater;
        return ans;


//
//
//        var expectedLessThan = ((n + 1) * lessthan) - n;
//
//        var rests = expectedLessThan - totalLessthan;
//
//        if (largest > n) return  tasks.length;
//        var total = largest * tasks.length;
//
//        return total < n ? total : Math.max(total - n, total);
////        total = Math.max(total, n) / map.size();
////
//        return Math.max(total - n, total);

    }
}
