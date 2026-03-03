package dev.expert.interview;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ConcurrencyCounterTest {

    @Test
    void countsExactly() {
        int threads = 10;
        int perThread = 1000;
        int result = ConcurrencyCounter.runCounter(threads, perThread);
        assertThat(result).isEqualTo(threads * perThread);
    }
}
