package gotham.asset.mgmt.GAM06;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GAM06Test {

    private Solution getSolution() {
        return new Solution1(); // swap to Answer to verify
    }

    @Test
    void testBasicAdd() {
        assertEquals(22, getSolution().bitMaze(12, 10));
    }

    @Test
    void testZeros() {
        assertEquals(0, getSolution().bitMaze(0, 0));
    }

    @Test
    void testOneZero() {
        assertEquals(5, getSolution().bitMaze(5, 0));
    }

    @Test
    void testZeroOne() {
        assertEquals(7, getSolution().bitMaze(0, 7));
    }

    @Test
    void testLargeNumbers() {
        assertEquals(1000, getSolution().bitMaze(999, 1));
    }

    @Test
    void testSameNumbers() {
        assertEquals(100, getSolution().bitMaze(50, 50));
    }

    @Test
    void testPowersOfTwo() {
        assertEquals(48, getSolution().bitMaze(16, 32));
    }
}
