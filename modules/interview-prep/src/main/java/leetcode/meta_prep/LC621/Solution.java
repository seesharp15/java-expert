package leetcode.meta_prep.LC621;

/**
 * 621. Task Scheduler
 * Given tasks as chars and a cooldown n, return least intervals to finish all tasks.
 */
public class Solution {
    public int leastInterval(char[] tasks, int n) {
        if (tasks == null || tasks.length == 0) return 0;
        int[] freq = new int[26];
        int max = 0, maxCount = 0;
        for (char c : tasks) {
            int f = ++freq[c - 'A'];
            if (f > max) { max = f; maxCount = 1; }
            else if (f == max) { maxCount++; }
        }
        int partCount = max - 1;
        int partLength = n - (maxCount - 1);
        int emptySlots = partCount * Math.max(0, partLength);
        int pending = tasks.length - max * maxCount;
        int idles = Math.max(0, emptySlots - pending);
        return tasks.length + idles;
    }
}
