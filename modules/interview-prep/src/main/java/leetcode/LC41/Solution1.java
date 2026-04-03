package leetcode.LC41;

public class Solution1 extends Solution {


    public int firstMissingPositive(int[] nums) {
        var total = nums.length;

        for (var i = 0; i < nums.length; i++) {

            while(nums[i] >= 1                       //only positive/ & non-zero
                    && nums[i] <= total              //within range of array
                    && nums[nums[i] - 1] != nums[i]) //not cyclic
            {
                var correctIndex = nums[i] - 1;
                swap(nums, i, correctIndex);
            }
        }

        for(var i = 0; i < nums.length;i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        return total + 1;


    }

    private void swap(int n[], int a, int b) {
        var tmp = n[a];
        n[a] = n[b];
        n[b] = tmp;
    }
}
