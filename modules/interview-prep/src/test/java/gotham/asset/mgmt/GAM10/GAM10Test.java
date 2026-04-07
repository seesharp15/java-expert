package gotham.asset.mgmt.GAM10;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GAM10Test {

    private Solution getSolution() {
        return new Solution1(); // swap to Answer to verify
    }

    @Test
    void testRotateLeftBy6() {
        assertArrayEquals(new int[]{6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,1,2,3,4,5},
                getSolution().rotateLeft(new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25}, 6));
    }

    @Test
    void testRotateLeftByTwo() {
        assertArrayEquals(new int[]{3, 4, 5, 1, 2},
                getSolution().rotateLeft(new int[]{1, 2, 3, 4, 5}, 2));
    }

    @Test
    void testRotateLeftByZero() {
        assertArrayEquals(new int[]{1, 2, 3},
                getSolution().rotateLeft(new int[]{1, 2, 3}, 0));
    }

    @Test
    void testRotateLeftByLength() {
        assertArrayEquals(new int[]{1, 2, 3},
                getSolution().rotateLeft(new int[]{1, 2, 3}, 3));
    }

    @Test
    void testRotateLeftByMoreThanLength() {
        assertArrayEquals(new int[]{2, 3, 1},
                getSolution().rotateLeft(new int[]{1, 2, 3}, 4));
    }

    @Test
    void testRotateLeftByNegative() {
        // Negative k means rotate right
        assertArrayEquals(new int[]{3, 1, 2},
                getSolution().rotateLeft(new int[]{1, 2, 3}, -1));
    }

    @Test
    void testSingleElement() {
        assertArrayEquals(new int[]{42},
                getSolution().rotateLeft(new int[]{42}, 5));
    }

    @Test
    void testEmptyArray() {
        assertArrayEquals(new int[]{},
                getSolution().rotateLeft(new int[]{}, 3));
    }

    @Test
    void testNullArray() {
        assertArrayEquals(new int[]{},
                getSolution().rotateLeft(null, 3));
    }
}
