package leetcode.LC994;

import java.util.*;

public class Solution5 extends Solution {
    private static final int[][] offsets = {{1, 0}, {-1,0},{0,1},{0,-1}};

    @Override
    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) return -1;

        var q = new ArrayDeque<int[]>();
        var fresh = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    q.offer(new int[] {i, j});
                } else if (grid[i][j] == 1){
                    fresh++;
                }
            }
        }

        if (q.isEmpty() && fresh == 0) return 0;
        if (q.isEmpty()) return -1;
        if (fresh == 0) return 0;

        var minutes = 0;
        while(!q.isEmpty()) {
            var size = q.size();
            for (int i = 0; i < size; i++) {
                var orange = q.poll();
                if (orange == null || orange.length != 2) continue;

                var row = orange[0];
                var col = orange[1];

                if (grid[row][col] == 1) {
                    fresh--;
                    grid[row][col] = 2;
                }
                if (fresh == 0) return minutes;

                for (var offset : offsets) {
                    var nr = row + offset[0];
                    var nc = col + offset[1];

                    if (nr < 0 || nc < 0 || nr >= grid.length || nc >= grid[0].length) continue;
                    if (grid[nr][nc] == 1) q.offer(new int[]{nr, nc});
                }
            }
            minutes++;
        }
        return -1;
    }
}
