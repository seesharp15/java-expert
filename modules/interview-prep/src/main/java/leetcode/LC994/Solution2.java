package leetcode.LC994;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution2 extends Solution {
    @Override
    public int orangesRotting(int[][] grid) {

        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) return 0;

        var rows = grid.length;
        var cols = grid[0].length;

        Queue<int[]> q = new ArrayDeque<>();
        var fresh = 0;

        for (var r = 0; r < rows; r++) {
            for (var c = 0; c < cols; c++) {
                if (grid[r][c] == 2) {
                    q.offer(new int[]{r, c});
                } else if (grid[r][c] == 1) {
                    fresh++;
                }
            }
        }

        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while (!q.isEmpty() && fresh > 0) {
            var size = q.size();
            for (var i = 0; i < size; i++) {
                var cell = q.poll();
                var r = cell[0];
                var c = cell[1];

                for (var dir : directions) {
                    var nextRow = r + dir[0];
                    var nextCol = c + dir[1];

                    if (nextRow < 0 || nextRow >= rows || nextCol < 0 || nextCol >= cols) continue; //out of bounds
                    if (grid[nextRow][nextCol] != 1) continue;

                    grid[nextRow][nextCol] = 2;
                    fresh--;
                    q.offer(new int[]{nextRow, nextCol});

                }
            }

            //minutes++;
        }


        return 0;
    }
}
