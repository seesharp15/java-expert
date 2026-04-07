package gotham.asset.mgmt.GAM01;

public class Solution1 extends Solution {

/*
*
Solution
GAM01 - Debug the Running Sum
Given an array of integers, return a new array where each element at index i is the sum of all elements from index 0 to i (inclusive).
Example: [1, 2, 3, 4] -> [1, 3, 6, 10]
DEBUGGING CHALLENGE: The original code below has a subtle bug. Fix it.
    public int[] runningSum(int[] nums) {
        int[] result = new int[nums.length];
        result[0] = nums[0];
        for (int i = 1; i <= nums.length; i++) {
            result[i] = result[i-1] + nums[i];
        }
        return result;
    }
    * */
    @Override
    public int[] runningSum(int[] nums) {
        if (nums == null || nums.length == 0) return nums;
        int[] result = new int[nums.length];
        result[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            result[i] = result[i - 1] + nums[i];
        }
        return result;
    }
}
