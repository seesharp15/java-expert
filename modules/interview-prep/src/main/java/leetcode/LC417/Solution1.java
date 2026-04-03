package leetcode.LC417;

import java.util.*;

/*
*
There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean.
* The Pacific Ocean touches the island's left and top edges, and the Atlantic Ocean touches the island's right and bottom edges.

The island is partitioned into a grid of square cells.
* You are given an m x n integer matrix heights where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).

The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, south, east, and west if
* the neighboring cell's height is less than or equal to the current cell's height. Water can flow from any cell adjacent to an ocean into the ocean.

Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water can flow from cell (ri, ci)
* to both the Pacific and Atlantic oceans.



Example 1:
*
* */
public class Solution1 extends Solution {

    @Override
    public List<List<Integer>> pacificAtlantic(int[][] heights) {


        if (heights == null) return List.of();

        var rows = heights.length;
        var cols = heights[0].length;

        var pacific = new boolean[rows][cols];
        var atlantic = new boolean[rows][cols];


        //top pacific, bottom atlantic
        //top and bottom
        for(var row = 0; row < heights.length; row++){
            search(row, 0, heights, pacific, -1); //top pacific
            search(row, heights[0].length-1, heights, atlantic, -1);
        }
        //left and right
        //left pacific, right atlantic
        for(var col = 0; col < heights[0].length; col++){

            search(0, col, heights, pacific, -1);
            search(heights.length-1, col, heights, atlantic, -1);
        }

        var result = new ArrayList<List<Integer>>();
        for(var row = 0; row < heights.length; row++){
            for(var col = 0; col < heights[0].length; col++) {
                if (atlantic[row][col] && pacific[row][col]) {
                    result.add(List.of(row, col));
                }
            }
        }

        //left and right
        return result;

    }

    private static int[][] directions = new int[][]{ {0, 1}, {0, -1}, {1, 0}, {-1, 0} };

    private void search(int row, int col, int[][] heights, boolean[][] visited, int previousValue) {

        if (row < 0 || col < 0 || row >= visited.length || col >= visited[0].length) return;

        if (visited[row][col]) return;
        if (previousValue > heights[row][col]) return;

        visited[row][col] = true;
        for (var direction : directions) {
            var nr = row + direction[0];
            var nc = col + direction[1];
            search(nr, nc, heights, visited, heights[row][col]);
        }

    }
}
