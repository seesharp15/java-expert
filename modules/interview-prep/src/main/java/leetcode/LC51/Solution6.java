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
public class Solution6 extends Solution {

    @Override
    public List<List<String>> solveNQueens(int n) {
        if (n <= 0) return List.of();
        var graph = new char[n][n];

        for (int i = 0; i < graph.length; i++) {
            Arrays.fill(graph[i], '.');
        }


        var result = new ArrayList<List<String>>();

        backtrack(0, n, graph, new HashSet<>(), new HashSet<>(), new HashSet<>(), result);

        return result;
    }

    private void backtrack(int row, int size, char[][] graph, HashSet<Integer> cols,
                           HashSet<Integer> diag1, HashSet<Integer> diag2, List<List<String>> result) {
        if (row >= size){
            result.add(makeString(graph));
        }

        for (int col = 0; col < size; col++) {


            var d1 = row - col;
            var d2 = row + col;

            if (cols.contains(col) || diag1.contains(d1) || diag2.contains(d2)) continue;


            if (row < 0 || row >= size) return;

            cols.add(col);
            diag1.add(d1);
            diag2.add(d2);
            graph[row][col] = 'Q';
            backtrack(row + 1, size, graph, cols, diag1, diag2, result);

            cols.remove(col);
            diag1.remove(d1);
            diag2.remove(d2);
            graph[row][col] = '.';
        }

    }

    private List<String> makeString(char[][] graph) {
        var strings = new ArrayList<String>();
        for(var chars: graph){
            strings.add(new String(chars));
        }
        return strings;
    }

}
