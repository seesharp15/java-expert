package dev.expert.interview;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TopKFrequentTest {

    @Test
    void findsTopKWithLexOrderOnTies() {
        var words = List.of("i", "love", "leetcode", "i", "love", "coding");
        assertThat(TopKFrequent.topK(words, 2)).containsExactly("i", "love");
    }

    @Test
    void handlesTiesLexicographically() {
        var words = List.of("a", "b", "c", "a", "b", "c");
        assertThat(TopKFrequent.topK(words, 3)).containsExactly("a", "b", "c");
    }
}
