package leetcode.LC51;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution1 extends Solution {

    @Override
    public List<List<String>> solveNQueens(int n) {
        var results = new ArrayList<List<String>>();
        var board = new char[n][n];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                board[r][c] = '.';
            }
        }

        backtrack(0, n, board, new HashSet<>(), new HashSet<>(), new HashSet<>(), results);
        return results;
    }

    private void backtrack(int row,
                           int n,
                           char[][] board,
                           Set<Integer> cols,
                           Set<Integer> diag1,
                           Set<Integer> diag2,
                           List<List<String>> results) {
        if (row == n) {
            results.add(toBoard(board));
            return;
        }

        for (int col = 0; col < n; col++) {
            int d1 = row - col;
            int d2 = row + col;
            if (cols.contains(col) || diag1.contains(d1) || diag2.contains(d2)) {
                continue;
            }

            // place
            board[row][col] = 'Q';
            cols.add(col);
            diag1.add(d1);
            diag2.add(d2);

            backtrack(row + 1, n, board, cols, diag1, diag2, results);

            // remove
            board[row][col] = '.';
            cols.remove(col);
            diag1.remove(d1);
            diag2.remove(d2);
        }
    }

    private List<String> toBoard(char[][] board) {
        var list = new ArrayList<String>(board.length);
        for (char[] row : board) {
            list.add(new String(row));
        }
        return list;
    }
}
