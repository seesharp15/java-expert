package dev.expert.streams;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ParallelPitfallTest {

    @Test
    void incorrectParallelCollectionShowsMissingElements() {
        var result = ParallelPitfall.incorrectParallelCollect(100_000);
        assertThat(result).hasSizeLessThan(100_000); // highly likely to miss due to race
    }

    @Test
    void fixedParallelCollectionIsComplete() {
        var result = ParallelPitfall.fixedParallelCollect(100_000);
        assertThat(result).hasSize(100_000);
        assertThat(result).contains(42);
    }
}
