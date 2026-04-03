package leetcode.LC215;

import java.util.concurrent.ThreadLocalRandom;

public class Solution1 extends Solution {
    @Override
    public int findKthLargest(int[] nums, int k) {


        var target = nums.length - k;

        return quicksort(nums, 0, nums.length - 1, target);

    }

    private int quicksort(int[] nums, int left, int right, int kthSmallest) {
        while(left <= right) {
            var randPart = getRandomPartition(nums, left, right);
            if (randPart == kthSmallest) {
                return nums[randPart];
            } else if (randPart < kthSmallest)  {
                left = randPart + 1;
            } else {
                right = randPart - 1;
            }

        }

        throw new RuntimeException("This should not happen");
    }

    private int getRandomPartition(int[] nums, int left, int right) {
        var partIndex = ThreadLocalRandom.current().nextInt(left, right + 1);
        swap(nums, partIndex, right);
        return partition(nums, left, right);
    }

    private int partition(int[] nums, int left, int right) {
        var pivot = nums[right];
        var index = left;
        for (int i = left; i < right ; i++) {
            if (nums[i] < pivot) {
                swap(nums, index, i);
                index++;
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
