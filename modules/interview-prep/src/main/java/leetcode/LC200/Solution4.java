package leetcode.LC200;

public class Solution4 extends Solution {


    @Override
    public int numIslands(char[][] grid) {

        //do a BFS, looking for islands... incrementing when found

        var islands = 0;
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) return 0;

        //var visited = new boolean[grid.length][grid[0].length];
        for(var row = 0; row<grid.length;row++){
            for(var col = 0; col < grid[0].length; col++) {
                //if (visited[row][col]) continue;

                if (grid[row][col] == '1') {
                    islands++;
                    //visitIsland(row, col, visited, grid);
                    visitIsland(row, col, grid);
                }
            }
        }

        return islands;
    }


    private static final int[][] offsets = new int[][]{ {0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    //private void visitIsland(int row, int col, boolean[][] visited, char[][] grid) {
    private void visitIsland(int row, int col, char[][] grid) {
        if (row >= grid.length || col >= grid[0].length || row < 0 || col < 0) return;


        if (grid[row][col] != '1') return;
        grid[row][col] = '0';
        for(var offset: offsets) {
            var nr = row + offset[0];
            var nc = col + offset[1];
            //visitIsland(nr, nc, visited, grid);
            visitIsland(nr, nc, grid);
        }

    }
}
