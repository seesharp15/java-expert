package leetcode.LC55;

/**
 * 55. Jump Game
 * Given an array nums where nums[i] represents the maximum jump length from index i,
 * return true if you can reach the last index starting at index 0.
 */
public class Solution {

    /**
     * Greedy: track the furthest reachable index as we scan.
     * If at any point the current index is beyond the furthest reach, we cannot proceed.
     */
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        int farthest = 0;
        for (int i = 0; i <= farthest && i < nums.length; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            if (farthest >= nums.length - 1) return true;
        }
        return nums.length == 1; // single element is trivially reachable
    }
}
