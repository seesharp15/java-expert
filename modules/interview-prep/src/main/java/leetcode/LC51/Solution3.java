package leetcode.LC51;


import java.sql.Array;
import java.util.*;
import java.util.stream.Collectors;


public class Solution3 extends Solution {


    private static void print(String name, HashSet<List<Integer>> grid, int originRow, int originCol, int n){

        var builder = new StringBuilder();
        builder.append(String.format("%s\n------------------\n", name));
        for(var row = 0; row < n; row++) {
            for (var col = 0; col < n; col++) {
                if (grid.contains(List.of(row, col))) {
                    var id = row == originRow && col == originCol ? "\t*" : "\tX";

                    builder.append(id);
                } else {
                    builder.append("\t.");
                }
            }
            builder.append('\n');
        }

        System.out.println(builder);

    }
    @Override
    public List<List<String>> solveNQueens(int n) {
        var result = new ArrayList<List<String>>();
        var allQueens = new HashSet<List<Integer>>();

        for(var row = 0; row < n; row++) {
            for (var col =0; col < n; col++) {

                if (allQueens.contains(List.of(row, col))) {
                    continue;
                }

                var queens = new HashSet<List<Integer>>();
                runTest(row, col, n, queens);

                if (queens.size() == n) {
                    result.add(getGridStrings(queens, n));
                    allQueens.addAll(queens);
                    print("queens", queens, row, col, n);

                    if (n > 1) { //add the horizontal mirror solution
                        var oppoQueens = new HashSet<List<Integer>>();
                        for(var queen: queens) {
                            var nr = n - queen.getFirst() - 1;
                            var nc = queen.getLast();
                            //if (nr != queen.getFirst())
                                oppoQueens.add(List.of( nr, nc));
                        }
                        allQueens.addAll(oppoQueens);
                        result.add(getGridStrings(oppoQueens, n));
                        //print("oppoQueens", oppoQueens,    n-row-1,col,n);

                        // add vertical mirror solution
                        var oppoQueens2 = new HashSet<List<Integer>>();
                        for(var queen: queens) {
                            var nr = queen.getFirst() ;
                            var nc = n - queen.getLast() - 1;

                            //if (nc != queen.getLast())
                                oppoQueens2.add(List.of( nr, nc));
                        }
                        allQueens.addAll(oppoQueens2);
                        result.add(getGridStrings(oppoQueens2, n));
                        //print("oppoQueens2", oppoQueens2,    n,n-col-1,n);
                    }
                }
            }
        }
        return result;
    }

    private HashSet<List<Integer>> runTest(int row, int col, int size, HashSet<List<Integer>> queens) {
        queens.add(List.of(row, col));
        var allMoves = new ArrayDeque<List<Integer>>();
        for(var r = 0; r < size; r++) {
            for (var c =0; c < size; c++) {
                allMoves.offer(List.of(r, c));
            }
        }

        var invalids = illegalMoves(row, col, size);
        allMoves.removeAll(invalids);

        while(!allMoves.isEmpty()) {
            var next = allMoves.pop();
            queens.add(List.of(next.getFirst(), next.getLast()));
            var inv = illegalMoves(next.getFirst(), next.getLast(), size);
            allMoves.removeAll(inv);
        }
        return queens;

    }

    private HashSet<List<Integer>> illegalMoves(int row, int col, int size) {
        if (row < 0 || col < 0 || row >= size || col >= size) return HashSet.newHashSet(0);

        var result = new HashSet<List<Integer>>();

        var offsets = new int[][]{{1, 1}, {1, -1}, {-1, 1}, {-1, -1}, {0, 1}, {0, -1}, {-1, 0}, {1, 0} };


        result.add(List.of(row, col));
        for (var offset : offsets) {
            walk(row, col, size, offset, result);
        }
        return result;
    }

    private void walk(int row, int col, int size, int[] offsets, HashSet<List<Integer>> diags) {
        var nr = row + offsets[0];
        var nc = col + offsets[1];
        diags.add(List.of(nr, nc));

        if (nc < 0 || nr < 0 || nr >= size|| nc >= size) return;
        walk(nr, nc, size, offsets, diags);
    }

    private static ArrayList<String> getGridStrings(HashSet<List<Integer>> queens, int n) {

        var rows = new ArrayList<String>();
        for(var row = 0; row < n; row++) {
            var builder = new StringBuilder();

            for (var col =0; col < n; col++) {
                if (queens.contains(List.of(row, col))) {
                    builder.append("Q");
                } else {
                    builder.append(".");
                }
            }
            rows.add(builder.toString());
        }
        return rows;
    }
}
