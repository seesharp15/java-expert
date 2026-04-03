package leetcode.LC128;


import java.util.*;

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
public class Solution2 extends Solution {

    @Override
    public int longestConsecutive(int[] nums) {
        var q = new PriorityQueue<Integer>();
        for(var num: nums) {
            q.offer(num);
        }

        int maxSet = 0;
        int currentSet = 1;
        var current = Integer.MIN_VALUE;
        var prev = current;

        while (!q.isEmpty()) {
            current = q.poll();
            if (current == prev) continue;

            currentSet  = prev + 1 != current ? 1 : currentSet + 1;

            if (currentSet > maxSet) maxSet = currentSet;
            prev = current;
        }

        return maxSet;
    }
}
