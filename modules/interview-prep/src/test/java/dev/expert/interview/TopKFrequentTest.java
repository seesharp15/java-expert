package dev.expert.interview;

import dev.expert.interview.TopKFrequent.TopKFrequent;
import dev.expert.interview.TopKFrequent.TopKFrequent2;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TopKFrequentTest {

    @Test
    void findsTopKWithLexOrderOnTies() {
        var words = List.of("i", "love", "leetcode", "i", "love", "coding");
        assertThat(TopKFrequent2.topK(words, 2)).containsExactly("i", "love");
    }

    @Test
    void findsTopKWithLexOrderOnTies2() {
        var words = List.of("i", "i", "i", "i", "i", "love", "leetcode", "i", "love", "coding", "lover", "lover", "lover");
        assertThat(TopKFrequent2.topK(words, 3)).containsExactly("i", "lover", "love");
    }

    @Test
    void handlesTiesLexicographically() {
        var words = List.of("a", "b", "c", "a", "b", "c");
        assertThat(TopKFrequent2.topK(words, 3)).containsExactly("a", "b", "c");
    }
}
