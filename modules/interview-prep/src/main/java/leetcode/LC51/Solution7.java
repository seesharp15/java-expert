package leetcode.LC51;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
/**
 * 51. N-Queens
 * Place n queens on an n x n chessboard so that no two queens attack each other.
 * Return all distinct board configurations using 'Q' and '.'.
 */
public class Solution7 extends Solution {

    // . Q . .
    // . . . Q
    // Q . . .
    // . . Q .

    @Override
    public List<List<String>> solveNQueens(int n) {
        var grid = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(grid[i], '.');
        }
        var result = new ArrayList<List<String>>();
        check(0, n, new HashSet<>(), new HashSet<>(),new HashSet<>(), grid, result);
        return result;
    }

    private void check(int row, int size, HashSet<Integer> cols, HashSet<Integer> diag1,
                       HashSet<Integer> diag2, char[][] grid, List<List<String>> result) {

        for(var col = 0; col < grid[0].length; col++){

            if (row >= size){
                var r = createGrid(grid);
                result.add(r);
                return;
            }

            var d1 = row + col;
            var d2 = row - col;


            if (row < 0 || row >= grid.length) continue;
            if(cols.contains(col) || diag1.contains(d1) || diag2.contains(d2)) continue;



            cols.add(col);
            diag1.add(d1);
            diag2.add(d2);
            grid[row][col] = 'Q';
            check(row + 1, size, cols, diag1, diag2, grid, result);

            grid[row][col] = '.';
            cols.remove(col);
            diag1.remove(d1);
            diag2.remove(d2);




        }


    }

    private List<String> createGrid(char[][] grid) {
        var r = new ArrayList<String>();
        for(var row: grid){
            var str = new String(row);
            r.add(str);
        }
        return r;
    }


}
