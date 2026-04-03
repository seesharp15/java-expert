package leetcode.LC994;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;

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

public class Solution4 extends Solution {
    private static int[][] DIRS =new int[][]{ {0, 1}, {0, -1}, {1, 0}, {-1, 0} };

    @Override
    public int orangesRotting(int[][] grid) {

        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) return -1;

        var q = new ArrayDeque<int[]>();

        var fresh = 0;
        //get all fresh oranges
        for(var i = 0; i<grid.length;i++) {
            for (var j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    q.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }

        var minutes = 0;
        while(!q.isEmpty() && fresh > 0) {
            minutes++;
            var size = q.size();
            for(var i = 0; i < size; i++) {
                var cell = q.poll();
                var row = cell[0];
                var col = cell[1];

                for(var d: DIRS) {
                    var nr = row + d[0];
                    var nc = col + d[1];

                    if (nr < 0 || nr >= grid.length || nc < 0 || nc >= grid[0].length) continue;
                    if (grid[nr][nc] != 1) continue;
                    //fresh.remove(List.of(nr, nc));
                    fresh--;
                    grid[nr][nc] = 2;
                    q.offer(new int[]{nr, nc});
                }
            }
        }

        return fresh > 0 ? -1 : minutes;
    }
}
