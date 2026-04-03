package leetcode;

import java.util.HashMap;

public class SolutionX {

    public int[] twoSum(int[] nums, int target) {

        var seen = new HashMap<Integer, Integer>(nums.length);

        for(var i = 0; i < nums.length; i++) {
            var num = nums[i];
            var complement = target - num;

            if (seen.containsKey(complement)) {
                return new int[]  { i, seen.get(complement) };
            }
            seen.put(num, i);
        }

        return new int[0];
    }
}
