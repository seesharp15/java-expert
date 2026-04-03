package leetcode.LC215;

import java.util.concurrent.ThreadLocalRandom;

public class Solution3 extends Solution {
    @Override
    public int findKthLargest(int[] nums, int k) {

        var target = nums.length - k;

        return quicksort(nums, 0, nums.length - 1, target);


    }

    private int quicksort(int[] nums, int left, int right, int target) {

        while (left <= right) {
            var pivotIndex = getRandomPartitionIndex(nums, left, right);
            if (pivotIndex == target) {
                return nums[pivotIndex];
            }
            else if (pivotIndex < target) {
                left = pivotIndex + 1;
            } else {
                right = pivotIndex - 1;
            }
        }
        throw new RuntimeException("this should not happen");
    }


    // [3, 2, 1, 0, 6, 4, 5]
    //pick=4



    private int getRandomPartitionIndex(int[] nums, int left, int right) {
        var index = ThreadLocalRandom.current().nextInt(left, right + 1);
        swap(nums, index, right);
        return partition(nums, left, right);
    }

    //pivot=5, index=0, right=10
    private int partition(int[] nums, int left, int right) {
        var pivot = nums[right];
        var index = left;

        for (int i = left; i < right; i++) {
            if (nums[i] < pivot) {
                swap(nums, index, i);
                index ++;
            }
        }

        swap(nums, index, right);
        return index;
    }


    private void swap(int[] nums, int from, int to) {
        var tmp = nums[to];
        nums[to] = nums[from];
        nums[from] = tmp;
    }


}

