package gotham.asset.mgmt.GAM18;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GAM18Test {

    private Solution getSolution() {
        return new Solution1(); // swap to Answer to verify
    }

    @Test
    void testBasicCase() {
        assertArrayEquals(new int[]{0, 3},
                getSolution().twoSumSorted(new int[]{1, 2, 3, 7}, 8));
    }

    @Test
    void testFirstAndLast() {
        assertArrayEquals(new int[]{0, 4},
                getSolution().twoSumSorted(new int[]{1, 3, 5, 7, 9}, 10));
    }

    @Test
    void testAdjacentElements() {
        assertArrayEquals(new int[]{1, 2},
                getSolution().twoSumSorted(new int[]{1, 4, 5, 6}, 9));
    }

    @Test
    void testNoSolution() {
        assertArrayEquals(new int[]{},
                getSolution().twoSumSorted(new int[]{1, 2, 3, 4}, 100));
    }

    @Test
    void testTwoElements() {
        assertArrayEquals(new int[]{0, 1},
                getSolution().twoSumSorted(new int[]{3, 5}, 8));
    }

    @Test
    void testNegativeNumbers() {
        assertArrayEquals(new int[]{0, 4},
                getSolution().twoSumSorted(new int[]{-5, -3, 0, 2, 4}, -1));
    }

    @Test
    void testCannotReuseSameElement() {
        // [1, 3, 5] with target 6: should find 1+5={0,2}, NOT 3+3
        assertArrayEquals(new int[]{0, 2},
                getSolution().twoSumSorted(new int[]{1, 3, 5}, 6));
    }

    @Test
    void testTwoElementsNoMatch() {
        assertArrayEquals(new int[]{},
                getSolution().twoSumSorted(new int[]{1, 2}, 5));
    }
}
