package leetcode.LC347;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class LC347Test {

    private Solution getSolution() {
        return new Solution2(); // swap implementations here
    }

    @Test
    void exampleCase_returnsTopTwo() {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int[] result = getSolution().topKFrequent(nums, 2);

        assertContainsSameElements(new int[]{1, 2}, result);
    }

    @Test
    void singleElementArray_returnsThatElement() {
        int[] nums = {42};
        int[] result = getSolution().topKFrequent(nums, 1);

        assertArrayEquals(new int[]{42}, result);
    }

    @Test
    void handlesNegativeNumbersAndKThree() {
        int[] nums = {4, -1, -1, 2, 2, 2, 4, 4, 5};
        int[] result = getSolution().topKFrequent(nums, 3);

        assertContainsSameElements(new int[]{4, 2, -1}, result);
    }

    @Test
    void kEqualsNumberOfUniques_returnsAll() {
        int[] nums = {1, 2, 3, 4};
        int[] result = getSolution().topKFrequent(nums, 4);

        assertContainsSameElements(new int[]{1, 2, 3, 4}, result);
    }

    @Test
    void tiesAreAllowed_returnsAnyTopK() {
        int[] nums = {5, 5, 6, 6, 7};
        int[] result = getSolution().topKFrequent(nums, 2);

        assertTrue(containsAll(result, 5, 6) || containsAll(result, 5, 7) || containsAll(result, 6, 7));
    }

    private static void assertContainsSameElements(int[] expected, int[] actual) {
        var exp = expected.clone();
        var act = actual.clone();
        Arrays.sort(exp);
        Arrays.sort(act);
        assertArrayEquals(exp, act);
    }

    private static boolean containsAll(int[] arr, int a, int b) {
        boolean hasA = false, hasB = false;
        for (int n : arr) {
            if (n == a) hasA = true;
            if (n == b) hasB = true;
        }
        return hasA && hasB;
    }
}
