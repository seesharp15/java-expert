package gotham.asset.mgmt.GAM01.answerkey;

import gotham.asset.mgmt.GAM01.Solution;

/**
 * GAM01 Answer - Debug the Running Sum
 *
 * Bug: the loop condition uses i <= nums.length which causes an
 * ArrayIndexOutOfBoundsException on the last iteration. Fix: i < nums.length.
 */
public class Answer extends Solution {

    @Override
    public int[] runningSum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        int[] result = new int[nums.length];
        result[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            result[i] = result[i - 1] + nums[i];
        }
        return result;
    }
}
