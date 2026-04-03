package leetcode.LC200;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LC200Test {
    private Solution getSolution() {
        return new Solution4(); // swap implementations here
    }

    @Test
    void returnsZeroForNullGrid() {
        var solution = getSolution();
        assertEquals(0, solution.numIslands(null));
    }

    @Test
    void returnsZeroForEmptyGrid() {
        var solution = getSolution();
        char[][] grid = new char[0][0];
        assertEquals(0, solution.numIslands(grid));
    }

    @Test
    void returnsZeroForAllWater() {
        var solution = getSolution();
        char[][] grid = {
                {'0', '0', '0'},
                {'0', '0', '0'},
                {'0', '0', '0'}
        };
        assertEquals(0, solution.numIslands(grid));
    }

    @Test
    void returnsOneForSingleLandCell() {
        var solution = getSolution();
        char[][] grid = {
                {'1'}
        };
        assertEquals(1, solution.numIslands(grid));
    }

    @Test
    void returnsZeroForSingleWaterCell() {
        var solution = getSolution();
        char[][] grid = {
                {'0'}
        };
        assertEquals(0, solution.numIslands(grid));
    }

    @Test
    void returnsOneForSingleLargeIsland() {
        var solution = getSolution();
        char[][] grid = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        assertEquals(1, solution.numIslands(grid));
    }

    @Test
    void returnsThreeForMultipleSeparateIslands() {
        var solution = getSolution();
        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        assertEquals(3, solution.numIslands(grid));
    }

    @Test
    void doesNotCountDiagonalConnectionsAsSameIsland() {
        var solution = getSolution();
        char[][] grid = {
                {'1', '0', '0'},
                {'0', '1', '0'},
                {'0', '0', '1'}
        };
        assertEquals(3, solution.numIslands(grid));
    }

    @Test
    void handlesSingleRow() {
        var solution = getSolution();
        char[][] grid = {
                {'1', '0', '1', '1', '0', '1'}
        };
        assertEquals(3, solution.numIslands(grid));
    }

    @Test
    void handlesSingleColumn() {
        var solution = getSolution();
        char[][] grid = {
                {'1'},
                {'0'},
                {'1'},
                {'1'},
                {'0'},
                {'1'}
        };
        assertEquals(3, solution.numIslands(grid));
    }

    @Test
    void handlesIslandTouchingBorders() {
        var solution = getSolution();
        char[][] grid = {
                {'1', '1', '0', '0'},
                {'1', '0', '0', '1'},
                {'0', '0', '1', '1'},
                {'0', '0', '0', '0'}
        };
        assertEquals(2, solution.numIslands(grid));
    }

    @Test
    void handlesComplexMixedShape() {
        var solution = getSolution();
        char[][] grid = {
                {'1', '0', '1', '0', '1'},
                {'1', '0', '1', '1', '1'},
                {'0', '0', '0', '0', '0'},
                {'1', '1', '0', '0', '1'},
                {'0', '1', '0', '1', '1'}
        };
        assertEquals(4, solution.numIslands(grid));
    }

    @Test
    void handlesCheckerboardPattern() {
        var solution = getSolution();

        char[][] grid = {
                {'1', '0', '1', '0'},
                {'0', '1', '0', '1'},
                {'1', '0', '1', '0'},
                {'0', '1', '0', '1'}
        };
        assertEquals(8, solution.numIslands(grid));
    }
}
