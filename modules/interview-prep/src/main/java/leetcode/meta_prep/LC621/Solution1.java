package leetcode.meta_prep.LC621;


import java.util.HashMap;

/**
 * 621. Task Scheduler
 */
public class Solution1 extends Solution {
    @Override
    public int leastInterval(char[] tasks, int n) {
        if (tasks == null) return 0;



        var map = new HashMap<Character, Integer>();

        for(var task: tasks) {
            map.put(task, map.getOrDefault(task, 0) + 1);
        }



        var pendingTasks = tasks.length;
        var totalTasks = tasks.length;
        var requiredCooldowns = 0;

        while(pendingTasks > 0) {
            var iterator = map.entrySet().iterator();
            var completed = 0;
            while(iterator.hasNext()) {
                pendingTasks--;
                completed++;
                var entry = iterator.next();
                var remaining = entry.getValue() - 1;
                if (remaining == 0) {
                    iterator.remove();
                    continue;
                }
                entry.setValue(remaining);
            }
            if (map.isEmpty()) break;
            requiredCooldowns += Math.max(n - (completed - 1), 0);
        }

        return totalTasks + requiredCooldowns;
    }
}
