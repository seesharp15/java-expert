package dev.expert.interview;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import dev.expert.interview.GraphProblems.GraphProblems2;
import static org.assertj.core.api.Assertions.assertThat;

class GraphProblemsTest {


    public int numSpecial(int[][] mat) {
        if (mat == null || mat.length < 1 || mat[0].length < 1) return 0;

        int[] colSum = new int[mat[0].length];
        int[] rowSum = new int[mat.length];

        for(var r = 0;r<mat.length;r++) {
            for (var c = 0; c < mat[0].length; c++) {
                if (mat[r][c] == 1) {
                    colSum[c] += 1;
                    rowSum[r] += 1;
                }
            }
        }

        int counter = 0;
        for(var r = 0;r<mat.length;r++) {
            for (var c = 0; c < mat[0].length; c++) {
                if (mat[r][c] == 1 && colSum[c] == 1 && rowSum[r] == 1) {
                    counter += 1;
                }
            }
        }

        return counter;
    }

    @Test
    void findsShortestPath() {
        Map<Integer, List<Integer>> graph = Map.of(
            1, List.of(2,3),
            2, List.of(4),
            3, List.of(4,5),
            4, List.of(6),
            5, List.of(6),
            6, List.of()
        );
        assertThat(GraphProblems2.shortestPath(graph, 1, 6)).containsExactly(1,2,4,6);
    }

    /*
    *
    * [1,2]
    * [1,3]
    * [2,4]
    * [3,4]
    * [3,5]
    * [4,6]
    * [5,6]
    * [6, ]
    * */
    @Test
    void returnsEmptyWhenNoPath() {
        Map<Integer, List<Integer>> graph = Map.of(1, List.of(2), 2, List.of());
        assertThat(GraphProblems2.shortestPath(graph, 1, 3)).isEmpty();
    }
}
