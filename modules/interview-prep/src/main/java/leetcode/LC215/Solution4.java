package leetcode.LC215;

import java.util.concurrent.ThreadLocalRandom;

public class Solution4 extends Solution {


    @Override
    public int findKthLargest(int[] nums, int k) {
        var target = nums.length - k; //quicksort is ascending so need to adjust k
        return quicksort(nums, 0, nums.length - 1, target);
    }

    private int quicksort(int[] nums, int left, int right, int kthIndex) {

        while(left <= right) {
            var partitionIndex = getRandomPartitionIndex(nums, left, right);
            if (partitionIndex == kthIndex) {
                return nums[partitionIndex];
            }
            else if (partitionIndex < kthIndex) {
                left = partitionIndex + 1;
            } else {
                right = partitionIndex - 1;
            }

        }

        throw new RuntimeException("this should not happen");
    }

    private int getRandomPartitionIndex(int[] nums, int left, int right) {
        var randomIndex = ThreadLocalRandom.current().nextInt(left, right + 1);
        swap(nums, randomIndex, right);
        return partition(nums, left, right);
    }

    private int partition(int[] nums, int left, int right) {
        var pivot = nums[right];
        var index = left;

        for (int i = 0; i < right; i++) {
            if (nums[index] < pivot) {
                swap(nums, index, i);
                index++;
            }
        }
        //swap back the pivot/right values
        swap(nums, index, right);
        return index;
    }

    private void swap(int[] nums, int from, int to) {
        var tmp = nums[to];
        nums[to] = nums[from];
        nums[from] = tmp;
    }

    //get random partition index = 3
    //swap random part with right
    // [3, 2, 1, 0, *5*, 4, ->6<-]
    // pivot = 5
    // from left - right-1, if nums[i] < pivot
    //swap them
    //move index up


    //[ 2, 1, 10, 12, 4, 6, 9, 7, 8, 3, 5]
    // pick: 3
    //[ 2, 1, 10, *12*, 4, 6, 9, 7, 8, 3, ->5<-]
    //[ 2, 1, 4, *12*, 10, 6, 9, 7, 8, 3, ->5<-]
    //[ 2, 1, 4, 3, 10, 6, 9, 7, 8, *12*, ->5<-]
    //[ 2, 1, 4, 3, 5, 6, 9, 7, 8, *12*, 10]


}
