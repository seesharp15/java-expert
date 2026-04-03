package leetcode.LC51;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Solution5 extends Solution {

    @Override
    public List<List<String>> solveNQueens(int n) {

        var grid = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(grid[i], '.');
        }

        var result = new ArrayList<List<String>>();

        var cols = new HashSet<Integer>();
        var diag1 = new HashSet<Integer>();
        var diag2 = new HashSet<Integer>();

        backtrack(0, n, grid, cols, diag1, diag2, result);
        return result;
    }

    private void backtrack(int row, int size, char[][] grid, HashSet<Integer> cols, HashSet<Integer> diag1, HashSet<Integer> diag2, List<List<String>> result) {

        if (row >= size) {
            result.add(stringify(grid));
        }

        for(var col = 0; col < size; col++) {
            var d1 = row - col;
            var d2 = row + col;

            if (cols.contains(col) || diag1.contains(d1) || diag2.contains(d2)){
                continue;
            }

            if (row < 0 || row >= size) continue;

            grid[row][col] = 'Q';
            cols.add(col);
            diag1.add(d1);
            diag2.add(d2);
            backtrack(row + 1, size, grid, cols, diag1, diag2, result);
            cols.remove(col);
            diag1.remove(d1);
            diag2.remove(d2);
            grid[row][col] = '.';
        }
    }

    private List<String> stringify(char[][] grid) {
        var result = new ArrayList<String>();
        for(var row: grid) {
            result.add(new String(row));
        }
        return result;
    }

}
