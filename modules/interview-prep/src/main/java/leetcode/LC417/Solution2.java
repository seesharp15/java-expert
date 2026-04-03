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
public class Solution2 extends Solution {

    @Override
    public List<List<Integer>> pacificAtlantic(int[][] heights) {


        //build graph of ocean-adjacent tiles (BFS) finding all valid paths

        var pacific = new HashMap<String, int[]>();
        var atlantic = new HashMap<String, int[]>();

        var pq = new ArrayDeque<int[]>();
        var aq = new ArrayDeque<int[]>();

        for(var row = 0; row < heights.length; row++) {
            pq.add(new int[] {row, 0});
            aq.add(new int[] {row, heights[0].length - 1});
        }

        for(var col = 0; col < heights[0].length; col++) {
            pq.add(new int[] {0, col});
            aq.add(new int[] {heights.length - 1, col});
        }

        search(aq, atlantic, heights);
        search(pq, pacific, heights);

        var result = new ArrayList<List<Integer>>();

        for(var entry: pacific.entrySet()) {
            if (atlantic.containsKey(entry.getKey())) {
                var v = entry.getValue();
                result.add(List.of(v[0], v[1]));
            }
        }

        Collections.sort(result, Comparator.comparingInt(List::getFirst));
        return result;

    }

    private void search(ArrayDeque<int[]> aq, HashMap<String, int[]> visited, int[][] heights) {
        while(!aq.isEmpty()) {
            var cell = aq.poll();
            var row = cell[0];
            var col = cell[1];

            if (visited.containsKey(String.format("[%s,%s]", row, col)))
                continue; //visited

            visited.put(String.format("[%s,%s]", row, col), cell);
            var value = heights[row][col];

            var directions = new int[][] { {0, -1}, {0, 1}, {1, 0}, {-1, 0} };

            for(var dir: directions) {
                var nr = row + dir[0];
                var nc = col + dir[1];

                if (nr >= 0 && nr < heights.length && nc >= 0 && nc < heights[0].length){
                    var v2 = heights[nr][nc];
                    if (value <= v2) {
                        aq.offer(new int[]{nr, nc});
                    }
                }
            }
        }
    }


}
