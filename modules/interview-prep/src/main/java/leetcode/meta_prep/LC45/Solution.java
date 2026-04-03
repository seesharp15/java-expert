package leetcode.meta_prep.LC45;

/**
 * 45. Jump Game II
 * Given an array nums where nums[i] is max jump length from i, return the minimum number of jumps to reach last index.
 */
public class Solution {
    public int jump(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        int jumps = 0, currentEnd = 0, farthest = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;
            }
        }
        return jumps;
    }
}
