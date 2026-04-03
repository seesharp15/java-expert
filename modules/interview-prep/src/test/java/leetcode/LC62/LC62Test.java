package leetcode.LC62;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LC62Test {

    private Solution getSolution() {
        return new Solution1(); // swap to Solution1 for your attempt
    }

    @Test
    void oneByOne_isOnePath() {
        assertEquals(1, getSolution().uniquePaths(1, 1));
    }

    @Test
    void oneByN_onlyOnePath() {
        assertEquals(1, getSolution().uniquePaths(1, 5));
        assertEquals(1, getSolution().uniquePaths(7, 1));
    }

    @Test
    void twoByTwo_isTwoPaths() {
        assertEquals(2, getSolution().uniquePaths(2, 2));
    }

    @Test
    void threeBySeven_matchesKnownValue() {
        assertEquals(28, getSolution().uniquePaths(3, 7));
    }

    @Test
    void largerGrid() {
        assertEquals(48620, getSolution().uniquePaths(10, 10));
    }

    @Test
    void invalidDimensions_returnZero() {
        assertEquals(0, getSolution().uniquePaths(0, 5));
        assertEquals(0, getSolution().uniquePaths(5, 0));
        assertEquals(0, getSolution().uniquePaths(-1, 3));
    }
}
