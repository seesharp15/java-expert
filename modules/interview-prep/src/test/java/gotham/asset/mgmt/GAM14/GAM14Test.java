package gotham.asset.mgmt.GAM14;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GAM14Test {

    private Solution getSolution() {
        return new Solution1(); // swap to Answer to verify
    }

    @Test
    void test3x3Matrix() {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        // Main diagonal: 1+5+9=15, Anti-diagonal: 3+5+7=15, subtract center 5 -> 25
        assertEquals(25, getSolution().diagonalSum(matrix));
    }

    @Test
    void test1x1Matrix() {
        int[][] matrix = {{42}};
        // Both diagonals are the same single element; odd size subtracts once -> 42
        assertEquals(42, getSolution().diagonalSum(matrix));
    }

    @Test
    void test2x2Matrix() {
        int[][] matrix = {
            {1, 2},
            {3, 4}
        };
        // Main diagonal: 1+4=5, Anti-diagonal: 2+3=5 -> 10
        assertEquals(10, getSolution().diagonalSum(matrix));
    }

    @Test
    void test4x4Matrix() {
        int[][] matrix = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 16}
        };
        // Main: 1+6+11+16=34, Anti: 4+7+10+13=34 -> 68
        assertEquals(68, getSolution().diagonalSum(matrix));
    }

    @Test
    void testAllZeros() {
        int[][] matrix = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
        };
        assertEquals(0, getSolution().diagonalSum(matrix));
    }

    @Test
    void testIdentityMatrix3x3() {
        int[][] matrix = {
            {1, 0, 0},
            {0, 1, 0},
            {0, 0, 1}
        };
        // Main: 1+1+1=3, Anti: 0+1+0=1, subtract center 1 -> 3
        assertEquals(3, getSolution().diagonalSum(matrix));
    }

    @Test
    void testNegativeValues() {
        int[][] matrix = {
            {-1, 2},
            {3, -4}
        };
        // Main: -1+-4=-5, Anti: 2+3=5 -> 0
        assertEquals(0, getSolution().diagonalSum(matrix));
    }
}
