package dev.expert.interview;

import dev.expert.interview.ConcurrencyCounter.ConcurrencyCounter;
import dev.expert.interview.ConcurrencyCounter.ConcurrencyCounter2;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ConcurrencyCounterTest {

    @Test
    void countsExactly() {
        int threads = 10;
        int perThread = 1000;
        int result = ConcurrencyCounter2.runCounter(threads, perThread);
        assertThat(result).isEqualTo(threads * perThread);
    }
}
