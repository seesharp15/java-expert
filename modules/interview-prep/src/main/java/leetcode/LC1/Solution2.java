package leetcode.LC1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Solution2 extends Solution {
/*
* Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.



Example 1:

Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
Example 2:

Input: nums = [3,2,4], target = 6
Output: [1,2]
Example 3:

Input: nums = [3,3], target = 6
Output: [0,1]


Constraints:

2 <= nums.length <= 104
-109 <= nums[i] <= 109
-109 <= target <= 109
Only one valid answer exists.
* */

    @Override
    public int[] twoSum(int[] nums, int target) {

        var map = new HashMap<Integer, Integer>();
        for(var i = 0; i < nums.length; i++) {
            var num = nums[i];
            if (map.containsKey(num)) {
                //the only time a repeating number matters is if the sum == target, otherwise it's not the answer, so discard it
                if (num * 2 == target) return new int[] {map.get(num), i};
                map.remove(num); //make sure there's no circular nonsense below
                continue;
            }
            map.put(num, i);
        }

        for (var cell: map.entrySet()) {
            var num = cell.getKey();
            var compliment = target - num;

            if (map.containsKey(compliment)) {
                return new int[] { cell.getValue(), map.get(compliment) };
            }
        }

        return new int[0];
    }
}
