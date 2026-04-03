package leetcode.LC286;

import java.util.ArrayDeque;

public class Solution {

    private static final int INF = Integer.MAX_VALUE;
    private static final int[][] DIRS = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1}
    };

    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0] == null || rooms[0].length == 0) {
            return;
        }

        int rows = rooms.length;
        int cols = rooms[0].length;
        ArrayDeque<int[]> q = new ArrayDeque<>();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (rooms[r][c] == 0) {
                    q.offer(new int[]{r, c});
                }
            }
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int row = cur[0];
            int col = cur[1];

            for (int[] d : DIRS) {
                int nr = row + d[0];
                int nc = col + d[1];

                if (nr < 0 || nr >= rows || nc < 0 || nc >= cols) continue;
                if (rooms[nr][nc] != INF) continue;

                rooms[nr][nc] = rooms[row][col] + 1;
                q.offer(new int[]{nr, nc});
            }
        }
    }
}

