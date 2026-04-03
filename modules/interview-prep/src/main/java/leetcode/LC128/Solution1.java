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
public class Solution1 extends Solution {

    record window(int index, int size) {}
    @Override
    public int longestConsecutive(int[] nums) {

        var maxWindow = 0;
        for (int i = 0; i < nums.length; i++) {

            var window = findWindow(nums, i);
            if (window.size > maxWindow)
                maxWindow = window.size;

            i = window.index;
        }

        return maxWindow;
    }

    private window findWindow(int[] nums, int i) {
        var startingPosition = i;
        while(i < nums.length) {
            var prev = nums[i];
            i++;

            var current = nums[i];

            if (prev != current) break;
        }

        return new window(i, startingPosition - i);
    }
}
