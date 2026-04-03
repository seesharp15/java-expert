package leetcode.LC51;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Solution4 extends Solution {


    @Override
    public List<List<String>> solveNQueens(int n) {
        var result = new ArrayList<List<String>>();

        var board = new char[n][n];
        for(var r = 0; r < n; r++) {
            Arrays.fill(board[r], '.');
        }

        var cols = new HashSet<Integer>();
        var diag1 = new HashSet<Integer>();
        var diag2 = new HashSet<Integer>();


        backtrack(0, n, board, cols, diag1, diag2, result);

        return result;

    }

    private void backtrack(int row, int size, char[][] board, HashSet<Integer> cols, HashSet<Integer> diag1, HashSet<Integer> diag2, ArrayList<List<String>> result) {
        if (row == size){
            result.add(buildBoard(board));
            return ;
        }

        for(var col = 0; col < size; col++){
            var d1 = row - col;
            var d2 = row + col;
            if (cols.contains(col) || diag1.contains(d1) || diag2.contains(d2)) {
                continue;
            }

            board[row][col] = 'Q';
            cols.add(col);
            diag1.add(d1);
            diag2.add(d2);
            backtrack(row + 1, size, board, cols, diag1, diag2, result);
            board[row][col] = '.';
            cols.remove(col);
            diag1.remove(d1);
            diag2.remove(d2);
        }

    }

    private List<String> buildBoard(char[][] board) {
        var result = new ArrayList<String>();
        for (char[] chars : board) {
            result.add(new String(chars));
        }
        return result;
    }
}
