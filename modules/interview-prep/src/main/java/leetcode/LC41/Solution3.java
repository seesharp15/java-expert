package leetcode.LC41;

public class Solution3 extends Solution {


    @Override
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) return 1;

        for (var i = 0; i < nums.length; i++) {

            var indx = nums[i] - 1;

            while (indx != i) {
                var num = nums[i];

                if (num <= 0 || num > nums.length) break;
                var old = nums[num - 1];

                if (old == num) break;

                nums[num - 1] = num;
                nums[i] = old;

            }
        }

        for(var i = 0;i<nums.length;i++){
            if (nums[i] - 1 != i) {
                return i + 1;
            }
        }

        return nums.length + 1;
    }
}
