package leetcode.LC128;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LC128Test {

    private final Solution solution = new Solution4();

    @Test
    void example1_returnsExpected() {
        int[] nums = {100, 4, 200, 1, 3, 2};

        assertEquals(4, solution.longestConsecutive(nums));
    }

    @Test
    void example2_returnsExpected() {
        int[] nums = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};

        assertEquals(9, solution.longestConsecutive(nums));
    }

    @Test
    void emptyArray_returnsZero() {
        int[] nums = {};

        assertEquals(0, solution.longestConsecutive(nums));
    }

    @Test
    void singleElement_returnsOne() {
        int[] nums = {42};

        assertEquals(1, solution.longestConsecutive(nums));
    }

    @Test
    void noConsecutiveNumbers_returnsOne() {
        int[] nums = {10, 30, 50, 70};

        assertEquals(1, solution.longestConsecutive(nums));
    }

    @Test
    void alreadyConsecutiveAscending_returnsFullLength() {
        int[] nums = {1, 2, 3, 4, 5, 6};

        assertEquals(6, solution.longestConsecutive(nums));
    }

    @Test
    void alreadyConsecutiveDescending_returnsFullLength() {
        int[] nums = {6, 5, 4, 3, 2, 1};

        assertEquals(6, solution.longestConsecutive(nums));
    }

    @Test
    void duplicatesDoNotIncreaseSequenceLength() {
        int[] nums = {1, 2, 2, 3, 4, 4, 5};

        assertEquals(5, solution.longestConsecutive(nums));
    }

    @Test
    void negativeNumbers_formConsecutiveSequence() {
        int[] nums = {-1, -2, -3, -4, 10};

        assertEquals(4, solution.longestConsecutive(nums));
    }

    @Test
    void mixOfNegativeAndPositive_returnsLongestSequence() {
        int[] nums = {-2, -1, 0, 1, 2, 10, 11};

        assertEquals(5, solution.longestConsecutive(nums));
    }

    @Test
    void multipleSequences_returnsLongestOne() {
        int[] nums = {100, 101, 102, 1, 2, 3, 4, 50, 51};

        assertEquals(4, solution.longestConsecutive(nums));
    }

    @Test
    void sequenceWithGaps_returnsLongestContiguousRun() {
        int[] nums = {1, 2, 4, 5, 7, 8, 9};

        assertEquals(3, solution.longestConsecutive(nums));
    }

    @Test
    void allElementsSame_returnsOne() {
        int[] nums = {7, 7, 7, 7};

        assertEquals(1, solution.longestConsecutive(nums));
    }

    @Test
    void extremeValues_doNotAccidentallyConnect() {
        int[] nums = {Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 1};

        assertEquals(2, solution.longestConsecutive(nums));
    }

    @Test
    void longestSequenceNotStartingAtSmallestValue_returnsCorrectLength() {
        int[] nums = {50, 51, 52, 3, 10, 11, 12, 13};

        assertEquals(4, solution.longestConsecutive(nums));
    }
}