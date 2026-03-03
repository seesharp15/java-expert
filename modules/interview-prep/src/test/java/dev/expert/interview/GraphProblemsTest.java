package dev.expert.interview;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class GraphProblemsTest {

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
        assertThat(GraphProblems.shortestPath(graph, 1, 6)).containsExactly(1,3,4,6);
    }

    @Test
    void returnsEmptyWhenNoPath() {
        Map<Integer, List<Integer>> graph = Map.of(1, List.of(2), 2, List.of());
        assertThat(GraphProblems.shortestPath(graph, 1, 3)).isEmpty();
    }
}
