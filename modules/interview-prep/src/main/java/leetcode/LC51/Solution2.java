package leetcode.LC51;

import java.sql.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 51. N-Queens
 * Place n queens on an n x n chessboard so that no two queens attack each other.
 * Return all distinct board configurations using 'Q' and '.'.
 */
public class Solution2 extends Solution {
    @Override
    public List<List<String>> solveNQueens(int n) {
        var dict = new HashSet<List<Integer>>();
        for (var row = 0; row < n; row++) {
            for (var col = 0; col < n; col++) {
                dict.add(List.of(row, col));
            }
        }

        var answer = new ArrayList<List<String>>();
        for (var row = 0; row < n; row++) {
            for (var col = 0; col < n; col++) {
                var graph = new char[n][n];var visited = new boolean[n][n];
                var moves = new HashSet<>(dict);
                graph[row][col] = 'Q';
                var qc = 0;
                moves.remove(List.of(row, col));
                var q = new ArrayDeque<>(moves);
                var result = new ArrayList<List<Integer>>();

                search(graph, visited, q, row, col, qc, result);
                if (result.size() == n) {
                    answer.add(copy(graph));
                }
            }
        }

        return answer;
    }

    private int[][] dirs = new int[][] { {0, 1}, {0 ,-1}, {1, 0}, {-1, 0} }; //, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}};
    private void search(char[][] graph, boolean[][] visited, ArrayDeque<List<Integer>> movesX, int row, int col, Integer qcount, List<List<Integer>> result) {
        if (row < 0 || col < 0 || row >= graph.length || col >= graph.length) return;
        if (visited[row][col]) return;
        visited[row][col] = true;

        graph[row][col] = 'Q';
        result.add(List.of(row, col));
        qcount ++;

        if(movesX == null || movesX.isEmpty()) return;
        var moves = new ArrayDeque<>(movesX.stream().toList());

        if(moves.isEmpty()) return;
        invalidate(moves, graph, visited, row, col);

        for(var d: dirs) {
            var nr = row + d[0];
            var nc = col + d[1];
            invalidate(moves, graph, visited, nr, nc);
        }

        for(var x = 0; x < graph.length; x++){
            invalidate(moves,graph,visited,   x, col); //
        }
        for(var x = 0; x < graph[0].length; x++){
            invalidate(moves, graph, visited, row, x); //
        }

        for(var i = 0; i<graph.length;i++){
            invalidate(moves, graph, visited, row + i, col + i);
            invalidate(moves, graph, visited, col + -i, row + -i);
            invalidate(moves,graph, visited, row + -i, col + i);
            invalidate(moves,graph, visited, col + i, row + -i);
        }

        for(var pos: moves){
            search(graph, visited, moves, pos.getFirst(), pos.getLast(), qcount, result);
        }
    }

    private void invalidate(ArrayDeque<List<Integer>> moves, char[][] graph, boolean[][] visited, int or, int oc) {
        moves.remove(List.of(or, oc));

        if (or >= 0 && or < graph.length && oc >= 0 && oc < graph[0].length) {
            if (visited[or][oc]) return;
            graph[or][oc] = '.';
            visited[or][oc] = true;
        }
    }

    private List<String> copy(char[][] in) {
        var r = new ArrayList<String>();

        for(var i = 0;i<in.length;i++){
            var builder = new StringBuilder();
            for(var j = 0;j<in[0].length;j++){
                builder.append(in[i][j]);
            }
            r.addFirst(builder.toString());
        }
        return r;
    }

}
