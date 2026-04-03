package leetcode.LC215;

import java.util.concurrent.ThreadLocalRandom;

public class Solution10 extends Solution {
    @Override
    public int findKthLargest(int[] nums, int k) {
        return quicksearch(nums, 0, nums.length - 1, nums.length - k);
    }


    private static Integer quicksearch(int[] nums, int left, int right, int k) {
        while(left <= right){
            var index = getIndexFromPartition(nums, left, right);
            if (index == k) {
                return nums[index];
            } else if (index < k) {
                //too far left
                left = index + 1;
            }
            else {
                //too far right
                right = index - 1;
            }
        }
        throw new RuntimeException("this should not happen!");
    }

    private static Integer getIndexFromPartition(int[] nums, int left, int right) {
        var randomIndex = ThreadLocalRandom.current().nextInt(left, right + 1);
        swap(nums, randomIndex, right);
        return partition(nums, left, right);
    }

    private static Integer partition(int[] nums, int left, int right) {
        var pivotValue = nums[right];
        for(var i = left; i < right; i++) {
            if (nums[i] < pivotValue) {
                swap(nums, i, left++);
            }
        }
        swap(nums, left, right);
        return left;
    }

    private static void swap(int[] nums, int a, int b) {
        var tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }
}
