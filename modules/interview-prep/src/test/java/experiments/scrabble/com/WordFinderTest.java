package experiments.scrabble.com;

import org.assertj.core.api.recursive.assertion.RecursiveAssertionNode;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordFinderTest {

    @Test
    public void test_word_finder() {


        var finder = new WordFinder(new DictionaryProvider());

        var rack = "NLFUOOYCN";
        var chars = rack.toCharArray();

        var words = finder.findWords(chars).toArray();

        Arrays.sort(words);


        for (var word : words)
            System.out.println(word);
    }



}
