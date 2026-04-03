package leetcode.LC215;


import java.util.Arrays;

/*
* Given an integer array nums and an integer k, return the kth largest element in the array.

Note that it is the kth largest element in the sorted order, not the kth distinct element.

Can you solve it without sorting?



Example 1:
1,2,3,4,5,6
Input: nums = [3,2,1,5,6,4], k = 2
Output: 5
Example 2:

Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
Output: 4


Constraints:

1 <= k <= nums.length <= 105
-104 <= nums[i] <= 104
* */
public class Solution0 extends Solution {


    @Override
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        var largest = new int[k];
        Arrays.fill(largest, Integer.MIN_VALUE);

        for (int n : nums) {
            if (n < largest[k - 1]) continue; // quick check to see if it's def not bigger;

            for (var j = 0; j < k; j++) {
                var largeNum = largest[j];
                if (n > largeNum) {
                    largeNum = n;

                    var prev = 0;
                    for (var x = j; x < k; x++) {
                        prev = largest[x];
                        largest[x] = largeNum;
                        largeNum = prev;
                    }
                    break;
                }
            }
        }

        return largest[k-1];
    }
}
