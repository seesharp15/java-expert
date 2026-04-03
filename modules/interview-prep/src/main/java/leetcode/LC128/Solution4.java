package leetcode.LC128;


//128. Longest Consecutive Sequence
//Medium
//
//Topics
//premium lock icon
//Companies
//Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
//
//You must write an algorithm that runs in O(n) time.
//
//
//
//Example 1:
//
//Input: nums = [100,4,200,1,3,2]
//Output: 4
//Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
//Example 2:
//
//Input: nums = [0,3,7,2,5,8,4,6,0,1]
//Output: 9
//Example 3:
//
//Input: nums = [1,0,1,2]
//Output: 3
//
//
//Constraints:
//
//0 <= nums.length <= 105
//-109 <= nums[i] <= 109
// /

import java.util.HashSet;

public class Solution4 extends Solution {

    @Override
    public int longestConsecutive(int[] numbers) {
        var nums = new HashSet<Integer>();
        for(var num: numbers) {
            nums.add(num);
        }

        int maxLen = 0;
        for(var num: nums) {
            var i = num;

            if (!nums.contains(i - 1)) { //there is a lower number in this "number string", so just skip it, we'll get to it
                var length = 0;

                while(nums.contains(i)) {
                    length++;
                    i++;
                }

                maxLen = Math.max(length, maxLen);
            }
        }

        return maxLen;
    }
}
