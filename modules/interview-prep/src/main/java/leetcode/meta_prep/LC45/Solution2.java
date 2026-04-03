package leetcode.meta_prep.LC45;

public class Solution2 extends Solution {
    @Override
    public int jump(int[] nums) {


        int maxEnd = 0, currentEnd = 0, jumpCount = 0;
        for(var i = 0; i < nums.length - 1; i++) {
            var distance = nums[i] + i;
            maxEnd = Math.max(maxEnd, distance);

            if (i == currentEnd) {
                currentEnd = maxEnd;
                jumpCount++;
            }
        }
        return jumpCount;
    }
}
