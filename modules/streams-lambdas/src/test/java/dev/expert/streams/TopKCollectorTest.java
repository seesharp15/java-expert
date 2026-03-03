package dev.expert.streams;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class TopKCollectorTest {

    @Test
    void collectsTopKDescending() {
        List<Integer> result = Stream.of(5, 1, 7, 3, 9, 2)
            .collect(TopKCollector.topK(3, Comparator.reverseOrder()));
        assertThat(result).containsExactly(9, 7, 5);
    }

    @Test
    void tiesRespectStability() {
        List<String> names = List.of("bob", "alice", "alex", "ben");
        List<String> top2 = names.stream()
            .collect(TopKCollector.topK(2, Comparator.comparingInt(String::length)));
        assertThat(top2).containsExactly("bob", "ben");
    }
}
