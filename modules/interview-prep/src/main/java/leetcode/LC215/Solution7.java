package leetcode.LC215;

import java.util.concurrent.ThreadLocalRandom;

public class Solution7 extends Solution {


    @Override
    public int findKthLargest(int[] nums, int k) {
        var target = nums.length - k;

        return quicksort(nums, 0, nums.length - 1, target);
    }

    private static int quicksort(int[] nums, int left, int right, int target) {
        while(left <= right) {
            var index = getPartitionIndex(nums, left, right);
            if (index == target){
                return nums[index];
            } else if (index < target) {
                left = index + 1;
            }
            else {
                right = index - 1;
            }
        }
        throw new RuntimeException("this should not happen");
    }

    private static int getPartitionIndex(int[] nums, int left, int right) {
        var index = ThreadLocalRandom.current().nextInt(left, right + 1);
        swap(nums, index, right);
        return partition(nums, left, right);
    }

    private static int partition(int[] nums, int left, int right) {
        var pivot = nums[right];
        var index = left;

        for(var i = left; i < right; i++){
            if (nums[i] < pivot) {
                swap(nums, i, index);
                index++;
            }
        }
        swap(nums, index, right);
        return index;
    }


    private static void swap(int[] nums, int a, int b) {
        var tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

}
