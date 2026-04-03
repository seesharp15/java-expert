package leetcode.LC1;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

//Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
//
//You may assume that each input would have exactly one solution, and you may not use the same element twice.
//
//You can return the answer in any order.
//
//
//
//Example 1:
//
//Input: nums = [2,7,11,15], target = 9
//Output: [0,1]
//Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
//Example 2:
//
//Input: nums = [3,2,4], target = 6
//Output: [1,2]
//Example 3:
//
//Input: nums = [3,3], target = 6
//Output: [0,1]
//
//
//Constraints:
//
//2 <= nums.length <= 104
//-109 <= nums[i] <= 109
//-109 <= target <= 109
//Only one valid answer exists.
//
//
//Follow-up: Can you come up with an algorithm that is less than O(n2) time complexity?
public class Solution3 extends Solution {

    @Override
    public int[] twoSum(int[] nums, int target) {

        if (nums == null || nums.length == 0) return new int[0];

        var numbers = new HashMap<Integer, Integer>(nums.length); // map of number to its index

        for (int i = 0; i < nums.length; i++) {
            var num = nums[i];

            if (numbers.containsKey(num) && num * 2 == target) { //weird corner case. all other dupes we ignore
                return new int[]{numbers.get(num), i};
            } else {
                numbers.put(num, i);
            }
        }

        for(var num: numbers.entrySet()) {
            var value = num.getKey();
            var compliment = target - value;

            if (numbers.containsKey(compliment)) {
                return new int[]{num.getValue(), numbers.get(compliment) };
            }
        }

        return new int[0];
    }
}
