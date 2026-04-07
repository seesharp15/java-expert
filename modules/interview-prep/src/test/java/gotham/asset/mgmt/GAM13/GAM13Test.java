package gotham.asset.mgmt.GAM13;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GAM13Test {

    private Solution getSolution() {
        return new Solution1(); // swap to Answer to verify
    }

    @Test
    void testInRange() {
        assertTrue(getSolution().inRange(5, 1, 10));
    }

    @Test
    void testAtLowerBound() {
        assertTrue(getSolution().inRange(1, 1, 10));
    }

    @Test
    void testAtUpperBound() {
        assertTrue(getSolution().inRange(10, 1, 10));
    }

    @Test
    void testBelowRange() {
        assertFalse(getSolution().inRange(0, 1, 10));
    }

    @Test
    void testAboveRange() {
        assertFalse(getSolution().inRange(11, 1, 10));
    }

    @Test
    void testNegativeRange() {
        assertTrue(getSolution().inRange(-3, -5, -1));
    }

    @Test
    void testSingleValueRange() {
        assertTrue(getSolution().inRange(5, 5, 5));
    }

    @Test
    void testJustOutsideSingleValueRange() {
        assertFalse(getSolution().inRange(6, 5, 5));
    }
}
