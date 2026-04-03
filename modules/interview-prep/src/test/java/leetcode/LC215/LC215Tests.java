package leetcode.LC215;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LC215Tests {

    private final Solution solution = new Solution10();

    @Test
    void example1_returnsExpected() {
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;

        assertEquals(5, solution.findKthLargest(nums, k));
    }

    @Test
    void example2_returnsExpected() {
        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int k = 4;

        assertEquals(4, solution.findKthLargest(nums, k));
    }

    @Test
    void singleElement_kIsOne_returnsOnlyElement() {
        int[] nums = {7};
        int k = 1;

        assertEquals(7, solution.findKthLargest(nums, k));
    }

    @Test
    void twoElements_kIsOne_returnsLargest() {
        int[] nums = {1, 2};
        int k = 1;

        assertEquals(2, solution.findKthLargest(nums, k));
    }

    @Test
    void twoElements_kIsTwo_returnsSmallest() {
        int[] nums = {1, 2};
        int k = 2;

        assertEquals(1, solution.findKthLargest(nums, k));
    }

    @Test
    void allElementsEqual_returnsThatValue() {
        int[] nums = {5, 5, 5, 5};
        int k = 3;

        assertEquals(5, solution.findKthLargest(nums, k));
    }

    @Test
    void includesNegativeNumbers_returnsCorrectValue() {
        int[] nums = {-1, -2, -3, -4, -5};
        int k = 2;

        assertEquals(-2, solution.findKthLargest(nums, k));
    }

    @Test
    void mixOfNegativeZeroAndPositive_returnsCorrectValue() {
        int[] nums = {-10, 0, 5, 3, -2, 8};
        int k = 3;

        assertEquals(3, solution.findKthLargest(nums, k));
    }

    @Test
    void duplicatesCountTowardRanking_returnsCorrectValue() {
        int[] nums = {4, 4, 4, 3, 3, 2, 1};
        int k = 5;

        assertEquals(3, solution.findKthLargest(nums, k));
    }

    @Test
    void kEqualsLength_returnsSmallestElement() {
        int[] nums = {9, 1, 8, 2, 7, 3};
        int k = nums.length;

        assertEquals(1, solution.findKthLargest(nums, k));
    }

    @Test
    void alreadySortedAscending_returnsCorrectValue() {
        int[] nums = {1, 2, 3, 4, 5, 6};
        int k = 2;

        assertEquals(5, solution.findKthLargest(nums, k));
    }

    @Test
    void alreadySortedDescending_returnsCorrectValue() {
        int[] nums = {6, 5, 4, 3, 2, 1};
        int k = 4;

        assertEquals(3, solution.findKthLargest(nums, k));
    }

    @Test
    void repeatedLargestValues_kWithinDuplicates_returnsLargest() {
        int[] nums = {10, 10, 10, 9, 8, 7};
        int k = 3;

        assertEquals(10, solution.findKthLargest(nums, k));
    }

    @Test
    void repeatedLargestValues_kAfterDuplicates_returnsNextValue() {
        int[] nums = {10, 10, 10, 9, 8, 7};
        int k = 4;

        assertEquals(9, solution.findKthLargest(nums, k));
    }
}