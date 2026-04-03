package leetcode.LC286;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class Solution2 extends Solution{

    private static int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};


    @Override
    public void wallsAndGates(int[][] rooms) {

        if (rooms == null || rooms.length == 0 || rooms[0] == null || rooms[0].length == 0) return;
        var rows = rooms.length;
        var cols = rooms[0].length;

        var q = new ArrayDeque<int[]>();

        for (var r = 0; r < rows; r++) {
            for (var c = 0; c < cols; c++) {
                if (rooms[r][c] == 0) {
                    q.offer(new int[] {r, c});
                }
            }
        }


        while (!q.isEmpty()) {

            var cell = q.poll() ;

            var row = cell[0];
            var col = cell[1];

            var value = rooms[row][col];

            for(var d: directions) {
                var nr = row + d[0];
                var nc = col + d[1];

                if (!(nr >= 0 && nc >= 0 && nr < rows && nc < cols)) continue;

                if (rooms[nr][nc] != Integer.MAX_VALUE) continue;
                rooms[nr][nc] = value + 1;
                q.offer(new int[] {nr, nc});
            }
        }


    }
}
