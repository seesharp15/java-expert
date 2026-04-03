package leetcode.LC994;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution3 extends Solution {

    /*
    You are given an m x n grid where each cell can have one of three values:

        0 representing an empty cell,
        1 representing a fresh orange, or
        2 representing a rotten orange.
    Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.

    Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.
    */

    @Override
    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) return 0;
        var q = new ArrayDeque<int[]>();

        var fresh = 0;
        for(var i = 0; i < grid.length; i++) {
            for(var j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 0) continue;
                else if (grid[i][j] == 1) fresh++;
                else q.add(new int[] {i,j});
            }
        }

        if (fresh == 0) return 0;
        var minutes = 0;
        var directions = new int[][] {{1, 0}, {-1, 0},  {0, 1}, {0, -1}};

        while(!q.isEmpty() && fresh > 0) {
            var size = q.size();

            for(var i = 0; i < size; i++) {
                var current = q.poll();
                var row = current[0];
                var col = current[1];

                for (var d : directions) {
                    var offsetRow = row + d[0];
                    var offsetCol = col + d[1];

                    if (offsetRow < 0 || offsetRow >= grid.length || offsetCol < 0 || offsetCol >= grid[0].length)
                        continue;
                    if (grid[offsetRow][offsetCol] != 1) continue;

                    grid[offsetRow][offsetCol] = 2;
                    q.offer(new int[]{offsetRow, offsetCol});
                    fresh--;
                }
            }
            minutes++;
        }
        return fresh == 0 ? minutes : -1;
    }
}
