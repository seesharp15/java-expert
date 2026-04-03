package leetcode.LC41;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LC41Test {
    private final Solution solution = new Solution4();

    @Test
    void example1() {
        assertEquals(3, solution.firstMissingPositive(new int[]{1, 2, 0}));
    }

    @Test
    void example2() {
        assertEquals(2, solution.firstMissingPositive(new int[]{3, 4, -1, 1}));
    }

    @Test
    void example3() {
        assertEquals(1, solution.firstMissingPositive(new int[]{7, 8, 9, 11, 12}));
    }

    @Test
    void singlePositiveOne() {
        assertEquals(2, solution.firstMissingPositive(new int[]{1}));
    }

    @Test
    void singlePositiveNotOne() {
        assertEquals(1, solution.firstMissingPositive(new int[]{2}));
    }

    @Test
    void singleZero() {
        assertEquals(1, solution.firstMissingPositive(new int[]{0}));
    }

    @Test
    void singleNegative() {
        assertEquals(1, solution.firstMissingPositive(new int[]{-1}));
    }

    @Test
    void allNegatives() {
        assertEquals(1, solution.firstMissingPositive(new int[]{-3, -2, -1}));
    }

    @Test
    void allZeros() {
        assertEquals(1, solution.firstMissingPositive(new int[]{0, 0, 0}));
    }

    @Test
    void consecutiveStartingAtOne() {
        assertEquals(4, solution.firstMissingPositive(new int[]{1, 2, 3}));
    }

    @Test
    void missingOne() {
        assertEquals(1, solution.firstMissingPositive(new int[]{2, 3, 4}));
    }

    @Test
    void duplicatesIncludingOne() {
        assertEquals(2, solution.firstMissingPositive(new int[]{1, 1}));
    }

    @Test
    void duplicatesMixed() {
        assertEquals(3, solution.firstMissingPositive(new int[]{1, 2, 2, 1}));
    }

    @Test
    void unsortedWithGap() {
        assertEquals(4, solution.firstMissingPositive(new int[]{2, 3, 1, 5}));
    }

    @Test
    void numbersOutOfRange() {
        assertEquals(1, solution.firstMissingPositive(new int[]{100, 200, 300}));
    }

    @Test
    void mixOfValidInvalidAndDuplicates() {
        assertEquals(5, solution.firstMissingPositive(new int[]{3, 4, -1, 1, 2, 2}));
    }

    @Test
    void alreadyInCorrectPlaces() {
        assertEquals(6, solution.firstMissingPositive(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    void repeatedLargeAndSmallValues() {
        assertEquals(3, solution.firstMissingPositive(new int[]{1, 1, 2, 1000, 1000}));
    }

    @Test
    void emptyArray() {
        assertEquals(1, solution.firstMissingPositive(new int[]{}));
    }

    @Test
    void trickyCaseWithCyclePotential() {
        assertEquals(2, solution.firstMissingPositive(new int[]{1, 1, 0, -1, -2}));
    }

    @Test
    void anotherCommonTrickyCase() {
        assertEquals(3, solution.firstMissingPositive(new int[]{2, 1, 0}));
    }

    @Test
    void largerGapAfterPrefix() {
        assertEquals(6, solution.firstMissingPositive(new int[]{5, 3, 2, 1, 4, 7, 8}));
    }

}

