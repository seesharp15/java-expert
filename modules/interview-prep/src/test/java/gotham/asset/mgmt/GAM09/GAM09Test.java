package gotham.asset.mgmt.GAM09;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GAM09Test {

    private Solution getSolution() {
        return new Solution1(); // swap to Answer to verify
    }

    @Test
    void testFactorialOfFive() {
        assertEquals(120, getSolution().mystery(5));
    }

    @Test
    void testFactorialOfZero() {
        assertEquals(1, getSolution().mystery(0));
    }

    @Test
    void testFactorialOfOne() {
        assertEquals(1, getSolution().mystery(1));
    }

    @Test
    void testFactorialOfTwo() {
        assertEquals(2, getSolution().mystery(2));
    }

    @Test
    void testFactorialOfThree() {
        assertEquals(6, getSolution().mystery(3));
    }

    @Test
    void testFactorialOfTen() {
        assertEquals(3628800, getSolution().mystery(10));
    }

    @Test
    void testNegativeInput() {
        assertEquals(1, getSolution().mystery(-1));
    }
}
