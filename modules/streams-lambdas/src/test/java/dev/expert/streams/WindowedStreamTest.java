package dev.expert.streams;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class WindowedStreamTest {

    @Test
    void producesSlidingWindows() {
        List<List<Integer>> windows = WindowedStream.windowed(Stream.of(1,2,3,4,5), 3, 1).toList();
        assertThat(windows).containsExactly(
            List.of(1,2,3),
            List.of(2,3,4),
            List.of(3,4,5)
        );
    }

    @Test
    void stepsCanSkip() {
        List<List<Integer>> windows = WindowedStream.windowed(Stream.of(1,2,3,4,5,6), 2, 3).toList();
        assertThat(windows).containsExactly(List.of(1,2), List.of(4,5));
    }
}
