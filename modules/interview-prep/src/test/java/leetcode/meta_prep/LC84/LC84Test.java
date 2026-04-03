package leetcode.meta_prep.LC84;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LC84Test {

    private Solution getSolution() {
        return new Solution3(); // swap to Solution1 for your attempt
    }

    @Test
    void example1() {
        assertEquals(10, getSolution().largestRectangleArea(new int[]{2,1,5,6,2,3}));
    }

    @Test
    void monotoneIncreases() {
        assertEquals(6, getSolution().largestRectangleArea(new int[]{1,2,3}));
    }

    @Test
    void uniformHeights() {
        assertEquals(9, getSolution().largestRectangleArea(new int[]{3,3,3}));
    }

    @Test
    void maxAreaIsWidthDriven() {
        assertEquals(13, getSolution().largestRectangleArea(new int[]{6, 1, 5, 1, 4, 1, 2, 2, 2, 1, 2, 2, 2}));
    }

}
