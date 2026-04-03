package leetcode.meta_prep.LC45;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LC45Test {

    private Solution solution = new Solution3();  //getSolution() {
//        return new Solution1(); // swap to Solution1 for your attempt
//    }

    @Test
    void example1() {
        assertEquals(2, solution.jump(new int[]{2,3,1,1,4}));
    }

    @Test
    void example2() {
        assertEquals(2, solution.jump(new int[]{2,3,0,1,4}));
    }

    @Test
    void singleElementNeedsZeroJumps() {
        assertEquals(0, solution.jump(new int[]{0}));
    }


    @Test
    void longArray_simpleProgression() {
        int[] nums = {1,1,1,1,1,1,1,1,1,1}; // 10 elements
        assertEquals(9, solution.jump(nums));
    }

    @Test
    void longArray_largeJumpsMixed() {
        int[] nums = {2,3,1,1,4,2,1,5,1,1,1,3,1}; // 13 elements
        assertEquals(4, solution.jump(nums));
    }

    @Test
    void longArray_optimalSkips() {
        int[] nums = {5,1,1,1,1,1,1,1,1,1,10}; // 11 elements
        assertEquals(6, solution.jump(nums));
    }

    @Test
    void longArray_manyZerosButReachable() {
        int[] nums = {4,0,0,0,2,0,0,1,3,0,1}; // 11 elements
        assertEquals(3, solution.jump(nums));
    }

    @Test
    void longArray_forcesFrequentJumps() {
        int[] nums = {2,1,2,1,2,1,2,1,2,1,2,1}; // 12 elements
        assertEquals(6, solution.jump(nums));
    }

    @Test
    void longArray_bigEarlyReach() {
        int[] nums = {10,1,1,1,1,1,1,1,1,1,1,1}; // 12 elements
        assertEquals(2, solution.jump(nums));
    }

    @Test
    void longArray_lateBoost() {
        int[] nums = {1,1,1,1,1,1,1,1,1,10}; // 10 elements
        assertEquals(9, solution.jump(nums));
    }

    @Test
    void longArray_multipleOptimalChoices() {
        int[] nums = {3,4,2,1,2,3,7,1,1,1,5,1,1}; // 13 elements
        assertEquals(4, solution.jump(nums));
    }

    @Test
    void longArray_plateauThenJump() {
        int[] nums = {1,1,1,1,1,1,1,1,1,1,1,1,5}; // 13 elements
        assertEquals(12, solution.jump(nums));
    }

    @Test
    void longArray_denseWithOptions() {
        int[] nums = {2,5,1,2,3,1,1,4,2,1,3,1,1,2,1}; // 15 elements
        assertEquals(6, solution.jump(nums));
    }
}
