package leetcode.LC286;

import java.util.ArrayList;
import java.util.HashMap;

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
public class Solution1 extends Solution {

    @Override
    public void wallsAndGates(int[][] rooms) {


        if (rooms == null || rooms.length == 0 || rooms[0] == null || rooms[0].length == 0) return;
        var rows = rooms.length;
        var cols = rooms[0].length;
        var gates = new ArrayList<int[]>();

        for (var r = 0; r < rows; r++) {
            for (var c = 0; c < cols; c++) {
                if (rooms[r][c] == 0) {
                    gates.add(new int[] {r, c});
                }
            }
        }


        for(var gate: gates) {
            var row = gate[0];
            var col = gate[1];

            bfs(row, col, rooms, new boolean[rows][cols], 0);
        }

    }

    private static int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private void bfs(int row, int col, int[][] rooms, boolean[][] visited, int distance) {


        if (row >= rooms.length || col >= rooms[0].length || row < 0 || col < 0) return;

        if (rooms[row][col] == -1) return;

        if (rooms[row][col] == Integer.MAX_VALUE && distance != 0) {
            rooms[row][col] = distance;
        }

        if (rooms[row][col] < distance) return;
        rooms[row][col] = distance;
        for(var dir : directions) {
            var nr = row + dir[0];
            var nc = col + dir[1];
            bfs(nr, nc, rooms, visited, distance+1);
        }
    }
}
