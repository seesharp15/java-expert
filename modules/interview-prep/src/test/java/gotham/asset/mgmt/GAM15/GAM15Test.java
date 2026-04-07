package gotham.asset.mgmt.GAM15;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GAM15Test {

    private Solution getSolution() {
        return new Solution1(); // swap to Answer to verify
    }

    @Test
    void testInitialThree() {
        // a=3: b = 3+3 = 6, c = 4-4 = 0, a ends at 3
        assertArrayEquals(new int[]{3, 6, 0}, getSolution().traceIncrements(3));
    }

    @Test
    void testInitialZero() {
        // a=0: b = 0+0 = 0, c = 1-1 = 0, a ends at 0
        assertArrayEquals(new int[]{0, 0, 0}, getSolution().traceIncrements(0));
    }

    @Test
    void testInitialOne() {
        // a=1: b = 1+1 = 2, c = 2-2 = 0, a ends at 1
        assertArrayEquals(new int[]{1, 2, 0}, getSolution().traceIncrements(1));
    }

    @Test
    void testInitialNegative() {
        // a=-5: b = -5 + -5 = -10, c = -4 - -4 = 0, a ends at -5
        assertArrayEquals(new int[]{-5, -10, 0}, getSolution().traceIncrements(-5));
    }

    @Test
    void testInitialTen() {
        // a=10: b = 10+10 = 20, c = 11-11 = 0, a ends at 10
        assertArrayEquals(new int[]{10, 20, 0}, getSolution().traceIncrements(10));
    }

    @Test
    void testAEqualsInitialValue() {
        // For any initialA, a should always end at initialA
        int[] result = getSolution().traceIncrements(42);
        assertEquals(42, result[0]);
    }

    @Test
    void testBIsTwiceInitialA() {
        // For any initialA, b should always be 2 * initialA
        int[] result = getSolution().traceIncrements(7);
        assertEquals(14, result[1]);
    }

    @Test
    void testCIsAlwaysZero() {
        // For any initialA, c should always be 0
        int[] result = getSolution().traceIncrements(99);
        assertEquals(0, result[2]);
    }
}
