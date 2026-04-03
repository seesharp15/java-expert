package leetcode.LC215;

import java.util.concurrent.ThreadLocalRandom;

public class Solution2 extends Solution {
    @Override
    public int findKthLargest(int[] nums, int k) {
        var target = nums.length - k;

        return qs(nums, 0, nums.length - 1, target);
    }

    private int qs(int[] nums, int left, int right, int kthSmallest) {
        while(left <= right) {
            var pi = randomPart(nums, left, right);
            if (pi == kthSmallest) {
                return nums[pi];
            }
            else if (pi < kthSmallest) {
                left = pi + 1;
            } else {
                right = pi - 1;
            }
        }

        throw new RuntimeException("this should not happen.");
    }


    private int randomPart(int[] nums, int left, int right) {
        var pi = ThreadLocalRandom.current().nextInt(left, right + 1);
        swap(nums, pi, right);
        return part(nums, left, right);
    }

    private int part(int[] nums, int left, int right) {
        var pivot = nums[right];
        var index = left;

        for(var i = left; i < right; i++) {
            if (nums[i] < pivot) { //smaller elements to the left
                swap(nums, index, i);
                index ++;
            }
        }
        swap(nums, index, right);
        return index;
    }

    private void swap(int[] nums, int left, int right) {
        var tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }


}
