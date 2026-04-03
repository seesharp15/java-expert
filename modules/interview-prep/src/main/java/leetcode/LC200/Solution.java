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
public abstract class Solution {
    public abstract int numIslands(char[][] grid);

}