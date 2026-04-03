package leetcode.LC215;

import java.util.concurrent.ThreadLocalRandom;

public class Solution6 extends Solution {


    @Override
    public int findKthLargest(int[] nums, int k) {

        var target = nums.length - k; //qs matches ascending, so need to adjust
        return search(nums, 0, nums.length - 1, target);
    }

    private int search(int[] nums, int left, int right, int target) {

        while(left <= right) {
            var partitionIndex = getRandomPartitionIndex(nums, left, right);
            if (partitionIndex == target) {
                return nums[partitionIndex];
            }
            else if (partitionIndex < target) {
                left = partitionIndex + 1;
            } else {
                right = partitionIndex - 1;
            }
        }

        throw new RuntimeException("this should not happen");
    }

    private int getRandomPartitionIndex(int[] nums, int left, int right) {

        var index = ThreadLocalRandom.current().nextInt(left, right + 1); //helps mitigate bad single strategy
        swap(nums, index, right);
        return partition(nums, left, right);
    }

    private int partition(int[] nums, int left, int right) {

        var pivot = nums[right];
        var index = left;

        for(var i = left; i < right; i++){
            if (nums[i] < pivot) {
                swap(nums, i, index);
                index ++;
            }
        }
        swap(nums, index, right);
        return index;
    }

    private void swap(int[] nums, int a, int b) {
        var tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }


}
