package leetcode.LC286;

/*
* LeetCode 286 — Walls and Gates
You are given an m × n grid `rooms` initialized with these three possible values:
    -1 → a wall or obstacle
    0 → a gate
    INF → an empty room
Fill each empty room with the distance to its nearest gate.
If it is impossible to reach a gate, leave the room as INF.
Distance is calculated using Manhattan distance (up, down, left, right).
You must modify the grid in-place.
Example
Input
INF  -1   0  INF
INF INF INF  -1
INF  -1 INF  -1
 0   -1 INF INF
Where
INF = 2147483647
Output
 3  -1   0   1
 2   2   1  -1
 1  -1   2  -1
 0  -1   3   4
* */

import java.util.ArrayDeque;

public class Solution3 extends Solution {
    private int INF = Integer.MAX_VALUE;

    @Override
    public void wallsAndGates(int[][] rooms) {
        var q = new ArrayDeque<int[]>();

        for (var i = 0; i < rooms.length; i++) {
            for (var j = 0; j < rooms[0].length; j++) {

                if (rooms[i][j] == 0) {
                    q.offer(new int[]{i, j});
                }
            }
        }

        var dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        var wave = 0;

        while (!q.isEmpty()) {
            wave++;
            var size = q.size();

            for(var qi = 0; qi < size; qi++) {
                var loc = q.poll();

                for(var d: dirs) {
                    var nr = loc[0] + d[0];
                    var nc = loc[1] + d[1];

                    if (nr < 0 || nr >= rooms.length || nc < 0 || nc >= rooms[0].length)continue;

                    if (rooms[nr][nc] != INF) continue;

                    rooms[nr][nc] = wave;
                    q.offer(new int[] {nr, nc});
                }
            }


        }

    }
}
