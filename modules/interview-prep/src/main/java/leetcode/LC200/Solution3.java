package leetcode.LC200;

public class Solution3 extends Solution {


    @Override
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) return 0;
        //invariant: you can visit an entire island from any part of it

        var islandCount = 0;
        for(var row = 0; row < grid.length; row++) {
            for (var col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == '1') {
                    ++islandCount;
                    removeIsland(grid, row, col);
                }
            }
        }
        return islandCount;
    }

    private void removeIsland(char[][] grid, int row, int col) {
        if (row < 0 || col < 0) return;
        if (grid == null || grid.length == 0 || row >= grid.length || grid[0] == null || col >= grid[0].length) return;

        if (grid[row][col] == '0') return;
        grid[row][col] = '0';

        removeIsland(grid, row + 1, col);
        removeIsland(grid, row - 1, col);
        removeIsland(grid, row, col + 1);
        removeIsland(grid, row, col - 1);
    }
}
