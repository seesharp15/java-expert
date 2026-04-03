package leetcode.LC215;

import java.util.concurrent.ThreadLocalRandom;

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
public class Solution8 extends Solution {

    @Override
    public int findKthLargest(int[] nums, int k) {
        var targetIndex = nums.length - k;
        return quicksearch(nums, 0, nums.length - 1, targetIndex);
    }

    private int quicksearch(int[] nums, int left, int right, int kthIndex) {
        while(left <= right) {
            var partIndex = getRandomPartitionIndex(nums, left, right);

            if (partIndex == kthIndex) {
                return nums[partIndex];
            }else if(partIndex < kthIndex) {
                left = partIndex + 1;

            } else {
                right = partIndex - 1;
            }
        }
        throw new RuntimeException("this should not happen");
    }

    private int getRandomPartitionIndex(int[] nums, int left, int right) {
        var index = ThreadLocalRandom.current().nextInt(left, right + 1);
        swap(nums, index, right);
        return partition(nums, left, right);
    }

    private int partition(int[] nums, int left, int right) {
        var pivot = nums[right];
        var index = left;
        for(var i = index; i < right; i++) {
            if (nums[i] < pivot) {
                swap(nums, index, i);
                index++;
            }
        }
        swap(nums, index, right);
        return index;
    }

    private void swap(int[] nums, int a, int b){
        var tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

}

