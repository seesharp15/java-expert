package gotham.asset.mgmt.GAM01;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GAM01Test {

    private Solution getSolution() {
        return new Solution1(); // swap to Answer to verify
    }

    @Test
    void testBasicCase() {
        int[] result = getSolution().runningSum(new int[]{1, 2, 3, 4});
        assertArrayEquals(new int[]{1, 3, 6, 10}, result);
    }

    @Test
    void testSingleElement() {
        int[] result = getSolution().runningSum(new int[]{5});
        assertArrayEquals(new int[]{5}, result);
    }

    @Test
    void testNegativeNumbers() {
        int[] result = getSolution().runningSum(new int[]{-1, 2, -3, 4});
        assertArrayEquals(new int[]{-1, 1, -2, 2}, result);
    }

    @Test
    void testAllZeros() {
        int[] result = getSolution().runningSum(new int[]{0, 0, 0});
        assertArrayEquals(new int[]{0, 0, 0}, result);
    }

    @Test
    void testLargeValues() {
        int[] result = getSolution().runningSum(new int[]{1000000, 1000000, 1000000});
        assertArrayEquals(new int[]{1000000, 2000000, 3000000}, result);
    }

    @Test
    void testTwoElements() {
        int[] result = getSolution().runningSum(new int[]{3, 7});
        assertArrayEquals(new int[]{3, 10}, result);
    }

    @Test
    void testEmptyArray() {
        int[] result = getSolution().runningSum(new int[]{});
        assertArrayEquals(new int[]{}, result);
    }
}
