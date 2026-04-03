package leetcode.LC994;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Solution1 extends Solution {

    /**
     *You are given an m x n grid where each cell can have one of three values:
     *
     * 0 representing an empty cell,
     * 1 representing a fresh orange, or
     * 2 representing a rotten orange.
     * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
     *
     * Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.
     *
     *
     *
     * Example
     */

    int seen = 0;
    @Override
    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) return 0;

        var clusterCount = 0;
        var maxMins = 0;


        for (var i = 0; i < grid.length; i++) {
            for(var j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != 0) clusterCount++;

                if (grid[i][j] == 2) {
                    grid[i][j] = 1; //tmp
                    var maxDegrees = getMaxDegrees(grid, i, j, 0); //starts at 1 bc of first one
                    clusterCount --;
                    if (maxDegrees > maxMins) maxMins = maxDegrees;
                }
            }
        }

        if (clusterCount > 0) return -1;
        return maxMins;
    }

    private int getMaxDegrees(int[][] grid, int row, int col, int degree) {

        if (row < 0 || col < 0) return 0;
        if (grid == null || row >= grid.length || grid[row] == null || col >= grid[row].length) return 0; //invalid
        if (grid[row][col] != 1) return 0;
        grid[row][col] = 0;

        var left = getMaxDegrees(grid, row, col - 1, degree + 1);
        var right = getMaxDegrees(grid, row, col + 1, degree + 1);
        var up = getMaxDegrees(grid, row - 1, col, degree + 1);
        var down = getMaxDegrees(grid, row + 1, col, degree + 1);

        var max = Stream.of(left, right, up, down, 0, degree).max(Comparator.comparingInt(Integer::intValue));
        return max.get();
    }

}
