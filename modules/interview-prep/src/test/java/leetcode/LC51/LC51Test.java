package leetcode.LC51;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class LC51Test {

    private Solution getSolution() {
        return new Solution7(); // swap implementations here
    }

    @Test
    void nEquals1_hasSingleSolution() {
        var solutions = getSolution().solveNQueens(1);
        assertEquals(1, solutions.size());
        assertEquals(List.of("Q"), solutions.get(0));
        assertValidSolutions(solutions, 1);
    }

    @Test
    void nEquals2_hasNoSolutions() {
        var solutions = getSolution().solveNQueens(2);
        assertTrue(solutions.isEmpty());
    }

    @Test
    void nEquals3_hasNoSolutions() {
        var solutions = getSolution().solveNQueens(3);
        assertTrue(solutions.isEmpty());
    }

    @Test
    void nEquals4_hasTwoSolutions() {
        var solutions = getSolution().solveNQueens(4);
        assertEquals(2, solutions.size());
        assertValidSolutions(solutions, 4);
    }

    @Test
    void nEquals5_hasTenSolutions() {
        var solutions = getSolution().solveNQueens(5);
        assertEquals(10, solutions.size());
        assertValidSolutions(solutions, 5);
    }

    private void assertValidSolutions(List<List<String>> solutions, int n) {
        for (List<String> board : solutions) {
            assertEquals(n, board.size(), "row count mismatch");
            var cols = new HashSet<Integer>();
            var diag1 = new HashSet<Integer>();
            var diag2 = new HashSet<Integer>();

            for (int r = 0; r < n; r++) {
                String row = board.get(r);
                assertEquals(n, row.length(), "row length mismatch");

                int queensInRow = 0;
                for (int c = 0; c < n; c++) {
                    char cell = row.charAt(c);
                    if (cell == 'Q') {
                        queensInRow++;
                        assertTrue(cols.add(c), "duplicate column");
                        assertTrue(diag1.add(r - c), "duplicate diag1");
                        assertTrue(diag2.add(r + c), "duplicate diag2");
                    } else {
                        assertEquals('.', cell, "invalid char");
                    }
                }
                assertEquals(1, queensInRow, "each row must have one queen");
            }
            assertEquals(n, cols.size(), "should have one queen per column");
        }
    }
}
