package gotham.asset.mgmt.GAM07;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GAM07Test {

    private Solution getSolution() {
        return new Solution1(); // swap to Answer to verify
    }

    @Test
    void testExampleCase() {
        assertEquals(14, getSolution().accumulate(new int[]{3, 1, 4, 1, 5}));
    }

    @Test
    void testSingleElement() {
        assertEquals(7, getSolution().accumulate(new int[]{7}));
    }

    @Test
    void testAllZeros() {
        assertEquals(0, getSolution().accumulate(new int[]{0, 0, 0}));
    }

    @Test
    void testNegativeNumbers() {
        assertEquals(-6, getSolution().accumulate(new int[]{-1, -2, -3}));
    }

    @Test
    void testMixedPositiveNegative() {
        assertEquals(0, getSolution().accumulate(new int[]{-5, 5, -3, 3}));
    }

    @Test
    void testEmptyArray() {
        assertEquals(0, getSolution().accumulate(new int[]{}));
    }

    @Test
    void testNullArray() {
        assertEquals(0, getSolution().accumulate(null));
    }

    @Test
    void testLargeValues() {
        assertEquals(2000000, getSolution().accumulate(new int[]{1000000, 1000000}));
    }
}
