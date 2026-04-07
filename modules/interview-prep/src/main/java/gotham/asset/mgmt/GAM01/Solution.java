package gotham.asset.mgmt.GAM01;

public abstract class Solution {

    /**
     * GAM01 - Debug the Running Sum
     *
     * <p>Given an array of integers, return a new array where each element at index i
     * is the sum of all elements from index 0 to i (inclusive).</p>
     *
     * <p>Example: [1, 2, 3, 4] -&gt; [1, 3, 6, 10]</p>
     *
     * <p>DEBUGGING CHALLENGE: The original code below has a subtle bug. Fix it.</p>
     * <pre>
     *   public int[] runningSum(int[] nums) {
     *       int[] result = new int[nums.length];
     *       result[0] = nums[0];
     *       for (int i = 1; i <= nums.length; i++) {
     *           result[i] = result[i-1] + nums[i];
     *       }
     *       return result;
     *   }
     * </pre>
     */
    public abstract int[] runningSum(int[] nums);
}
