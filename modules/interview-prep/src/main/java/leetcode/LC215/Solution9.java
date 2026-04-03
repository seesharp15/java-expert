package leetcode.LC215;

import java.util.concurrent.ThreadLocalRandom;

public class Solution9 extends Solution {


    @Override
    public int findKthLargest(int[] nums, int k) {
        var index = nums.length - k;
        return quicksearch(nums, 0, nums.length - 1, index);
    }


    private int quicksearch(int[] nums, int left, int right, int kthIndex) {
        while(left <= right) {
            var index = getPivotIndex(nums, left, right);
            if (index == kthIndex) {
                return nums[index];
            }
            else if (index < kthIndex) {
                left = index + 1;

              //  right = index + 1;
                // left = right - 1;
            }else {
                right = index - 1;
             //   left = index - 1;

               // right = index + 1;
            }
        }

        throw new RuntimeException("this should never happen!");

    }

    private int getPivotIndex(int[] nums, int left, int right) {
        //
        // [2,4,7,1,2,6,5,8,0,9,6,5,4,3,7,2,3]
        // index = 5, swap with right
        // [2,4,7,1,2,*3*,5,8,0,9,6,5,4,3,7,2,->6<-]
        // [2,4,1,2,*3*,5,0,5,4,3,2,6,8,9,6,7,7]

        var randomIndex = ThreadLocalRandom.current().nextInt(left, right+1);
        swap(nums, randomIndex, right);
        return partition(nums, left, right);
    }

    private int partition(int[] nums, int left, int right) {
        var pivot = nums[right];
        var index = left;

        for(var i = index; i < right; i++) {
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
