package leetcode.meta_prep.LC45;

import java.util.Arrays;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;
import java.util.stream.Collectors;

/**
 * 45. Jump Game II
 * Minimum jumps to reach the end of the array.
 */
public class Solution1 extends Solution {
    @Override
    public int jump(int[] nums) {


        if (nums == null || nums.length < 2) return 0;

        var vals = Arrays.stream(nums).mapToObj(String::valueOf).toList();
        System.out.println("\n[" + String.join(",", vals)+"]");
        int jumps = 0, currentEnd = 0, farthest = 0;

        for(var i = 0; i < nums.length-1;i++) {
            System.out.println(String.format("%s ------------------------------", i));
           // System.out.println(String.format("\tfarthest = Math.max(%s, %s); // nums[%s] = %s", i, i + nums[i], i, nums[i]));

            var ce   = String.format("\tcurrentEnd = %s", currentEnd);
            var jmps = String.format("\tjumps      = %s", jumps);

            var preFurthest = farthest;
            farthest = Math.max(farthest, i+nums[i]);
            System.out.println(String.format("\tfarthest = Math.max(%s, %s); // nums[%s] (%s) + %s = %s -> %s", preFurthest, i + nums[i], i, nums[i], i, nums[i] + i, farthest));

            if (i == currentEnd) {
                System.out.println("\t....updating....");
                jumps++;
                currentEnd = farthest;

                ce += String.format(" -> %s", currentEnd);
                jmps += String.format(" -> %s", jumps);
            }

            System.out.println(ce);
            System.out.println(jmps);

        }
        return jumps;
    }
}
