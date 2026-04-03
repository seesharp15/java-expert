package leetcode.LC994;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LC994Test {


    private final Solution solution = new Solution5();

    @Test
    void orangesRotting_singleEmptyCell_returnsZero() {
        int[][] grid = {
                {0}
        };

        assertEquals(0, solution.orangesRotting(grid));
    }

    @Test
    void orangesRotting_singleRottenOrange_returnsZero() {
        int[][] grid = {
                {2}
        };

        assertEquals(0, solution.orangesRotting(grid));
    }

    @Test
    void orangesRotting_singleFreshOrange_returnsMinusOne() {
        int[][] grid = {
                {1}
        };

        assertEquals(-1, solution.orangesRotting(grid));
    }

    @Test
    void orangesRotting_allAlreadyRotten_returnsZero() {
        int[][] grid = {
                {2, 2},
                {2, 2}
        };

        assertEquals(0, solution.orangesRotting(grid));
    }

    @Test
    void orangesRotting_noOrangesOnlyEmptyCells_returnsZero() {
        int[][] grid = {
                {0, 0, 0},
                {0, 0, 0}
        };

        assertEquals(0, solution.orangesRotting(grid));
    }

    @Test
    void orangesRotting_standardExample_returnsFour() {
        int[][] grid = {
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}
        };

        assertEquals(4, solution.orangesRotting(grid));
    }

    @Test
    void orangesRotting_impossibleBecauseFreshIsBlocked_returnsMinusOne() {
        int[][] grid = {
                {2, 1, 1},
                {0, 1, 1},
                {1, 0, 1}
        };

        assertEquals(-1, solution.orangesRotting(grid));
    }

    @Test
    void orangesRotting_noInitialRottenButFreshExists_returnsMinusOne() {
        int[][] grid = {
                {1, 1, 1},
                {1, 1, 1}
        };

        assertEquals(-1, solution.orangesRotting(grid));
    }

    @Test
    void orangesRotting_multipleRottenSources_returnsTwo() {
        int[][] grid = {
                {2, 1, 1},
                {1, 1, 1},
                {1, 1, 2}
        };

        assertEquals(2, solution.orangesRotting(grid));
    }

    @Test
    void orangesRotting_freshOrangeAdjacentToRotten_returnsOne() {
        int[][] grid = {
                {2, 1}
        };

        assertEquals(1, solution.orangesRotting(grid));
    }

    @Test
    void orangesRotting_isolatedFreshOrange_returnsMinusOne() {
        int[][] grid = {
                {2, 0, 1}
        };

        assertEquals(-1, solution.orangesRotting(grid));
    }

    @Test
    void orangesRotting_lineSpread_returnsFour() {
        int[][] grid = {
                {2, 1, 1, 1, 1}
        };

        assertEquals(4, solution.orangesRotting(grid));
    }

    @Test
    void orangesRotting_columnSpread_returnsThree() {
        int[][] grid = {
                {2},
                {1},
                {1},
                {1}
        };

        assertEquals(3, solution.orangesRotting(grid));
    }

    @Test
    void orangesRotting_emptyCellsSeparateButDoNotBlockAllPaths_returnsTwo() {
        int[][] grid = {
                {2, 1, 0},
                {1, 1, 1},
                {0, 1, 1}
        };

        assertEquals(4, solution.orangesRotting(grid));
    }
}
