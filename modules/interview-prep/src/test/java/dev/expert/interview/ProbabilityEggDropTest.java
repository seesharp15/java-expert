package dev.expert.interview;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProbabilityEggDropTest {

    @Test
    void computesMinDropsForHundredFloors() {
        assertThat(ProbabilityEggDrop.minDropsTwoEggs(100)).isEqualTo(14);
    }

    @Test
    void smallFloors() {
        assertThat(ProbabilityEggDrop.minDropsTwoEggs(10)).isEqualTo(4);
    }
}
