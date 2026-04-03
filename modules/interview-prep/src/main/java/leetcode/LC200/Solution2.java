package leetcode.LC200;


/*
Given an m x n 2D binary grid  which represents a map of '1's (land) and '0's (water), return the number of islands.
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

Input: grid = [
        ["1","1","1","1","0"],
        ["1","1","0","1","0"],
        ["1","1","0","0","0"],
        ["0","0","0","0","0"]
        ]

        [0, 1, 2, 3]
        [0, 1,    3]
        [0, 1]

Output: 1
Example 2:

Input: grid = [
        [0, 1]
        [0, 1]
        [2]
        [3, 4]

        ["1","1","0","0","0"],
        ["1","1","0","0","0"],
        ["0","0","1","0","0"],
        ["0","0","0","1","1"]
        ]
Output: 3


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 300
grid[i][j] is '0' or '1'.
*/

public class Solution2 extends Solution {
    public int numIslands(char[][] grid) {
        if (grid == null) return 0;
        var islands = 0;
        //concept here is loop through the array, if an "island" is identified (i.e. any '1'), then increment number of islands, then find all adjacent "islands" and wipe them out so they're not recounted.

        for(var row = 0; row < grid.length; row++){
            for(var col = 0; col < grid[0].length; col++){

                if (grid[row][col] == '1') { //is island?
                    islands++;
                    sinkIsland(grid, row, col);
                }
            }
        }


        return islands;
    }

    private void sinkIsland(char[][] grid, int row, int col) {
        if (grid == null || grid.length < 1 || row >= grid.length || col >= grid[0].length || row < 0 || col < 0)
            return;

        if (grid[row][col] == '1') {
            grid[row][col] = '0'; //sink it!
            sinkIsland(grid, row - 1, col); //above
            sinkIsland(grid, row + 1, col); //below
            sinkIsland(grid, row, col - 1); //left
            sinkIsland(grid, row, col + 1); //right

        }
    }
}
