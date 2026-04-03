package leetcode.LC1;

public class SolutionX {

    public int[] twoSum(int[] nums, int target) {


        var candidates = new int[target];

        int found = 0;
        for(var i = 0;i<nums.length;i++){
            if (nums[i] < target) {
                candidates[i] = nums[i];
                if (found++ >= target) break; //we found all possible numbers
            }
        }

        //there's only one solution per number, so every number must have

        for(var i = 0; i < candidates.length / 2; i++) {
            var num = i + 1;

            var diff = target - num;

            if (candidates[diff-1] == diff) {
                return new int[]{ num, diff };
            }
        }
        return new int[0];
    }

}

