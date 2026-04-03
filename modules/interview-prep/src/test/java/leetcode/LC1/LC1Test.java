package leetcode.LC1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LC1Test {

    private Solution getSolution() {
        return new Solution3(); // swap to Solution2 etc
    }

    @Test
    void handlesSimpleExample() {
        var solution = getSolution();

        int[] result = solution.twoSum(new int[]{2,7,11,15}, 9);

        assertPair(result, 0, 1);
    }

    @Test
    void handlesDifferentOrder() {
        var solution = getSolution();

        int[] result = solution.twoSum(new int[]{3,2,4}, 6);

        assertPair(result, 1, 2);
    }

    @Test
    void handlesDuplicates() {
        var solution = getSolution();

        int[] result = solution.twoSum(new int[]{3,3}, 6);

        assertPair(result, 0, 1);
    }

    @Test
    void handlesNegativeNumbers() {
        var solution = getSolution();

        int[] result = solution.twoSum(new int[]{-3,4,3,90}, 0);

        assertPair(result, 0, 2);
    }

    @Test
    void handlesZeros() {
        var solution = getSolution();

        int[] result = solution.twoSum(new int[]{0,4,3,0}, 0);

        assertPair(result, 0, 3);
    }

    @Test
    void handlesLargerInput() {
        var solution = getSolution();

        int[] nums = {10,15,3,7,8,4};
        int[] result = solution.twoSum(nums, 17);

        assertPair(result, 0, 3); // 10 + 7
    }

    @Test
    void handlesIntegerExtremes() {
        var solution = getSolution();

        int[] nums = {Integer.MAX_VALUE, -1, 1, Integer.MIN_VALUE};
        int[] result = solution.twoSum(nums, 0);

        assertPair(result, 1, 2);
    }

    @Test
    void alwaysReturnsTwoIndices() {
        var solution = getSolution();

        int[] result = solution.twoSum(new int[]{1,2,3,4}, 5);

        assertNotNull(result);
        assertEquals(2, result.length);
    }

    @Test
    void indicesAreDifferent() {
        var solution = getSolution();

        int[] result = solution.twoSum(new int[]{1,2,3,4}, 5);

        assertNotEquals(result[0], result[1]);
    }

    private void assertPair(int[] result, int expected1, int expected2) {
        assertNotNull(result);
        assertEquals(2, result.length);

        boolean matches =
                (result[0] == expected1 && result[1] == expected2) ||
                        (result[0] == expected2 && result[1] == expected1);

        assertTrue(matches,
                "Expected indices " + expected1 + "," + expected2 +
                        " but got " + result[0] + "," + result[1]);
    }
}
