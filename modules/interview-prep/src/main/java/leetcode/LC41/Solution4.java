package leetcode.LC41;

public class Solution4 extends Solution {

    /*
    * Given an unsorted integer array nums. Return the smallest positive integer that is not present in nums.
    You must implement an algorithm that runs in O(n) time and uses O(1) auxiliary space.
    Example 1:

    Input: nums = [1,2,0]
    Output: 3
    Explanation: The numbers in the range [1,2] are all in the array.
    Example 2:

    Input: nums = [3,4,-1,1]
    Output: 2
    Explanation: 1 is in the array but 2 is missing.
    Example 3:

    Input: nums = [7,8,9,11,12]
    Output: 1
    Explanation: The smallest positive integer 1 is missing.


    Constraints:

    1 <= nums.length <= 105
    -231 <= nums[i] <= 231 - 1
    * */

    @Override
    public int firstMissingPositive(int[] nums) {

        for(var i = 0; i<nums.length;i++) {


            var current = nums[i];
            var index = nums[i]-1;

            while (current - 1 != i) {

                if (index >= nums.length) break;
                if (index < 0) break;

                //swap
                var old = nums[index];
                if (old == current) break;
                nums[index] = current;
                nums[i] = old;

                index = nums[i]-1;
                current = old;
            }


        }


        for(var i = 0;i<nums.length;i++) {
            if (i != nums[i] - 1)
                return i + 1;
        }
        return nums.length + 1;
    }
}
