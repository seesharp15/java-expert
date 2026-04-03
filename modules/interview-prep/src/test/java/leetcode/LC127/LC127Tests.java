package leetcode.LC127;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class LC127Tests {

    private final Solution solution = new Solution7();

    @Test
    void exampleCase() {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = List.of("hot", "dot", "dog", "lot", "log", "cog");

        int result = solution.ladderLength(beginWord, endWord, wordList);

        assertEquals(5, result); // hit -> hot -> dot -> dog -> cog
    }

    @Test
    void endWordMissing_returnsZero() {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = List.of("hot", "dot", "dog", "lot", "log");

        int result = solution.ladderLength(beginWord, endWord, wordList);

        assertEquals(0, result);
    }

    @Test
    void directTransformation() {
        String beginWord = "hit";
        String endWord = "hot";
        List<String> wordList = List.of("hot");

        int result = solution.ladderLength(beginWord, endWord, wordList);

        assertEquals(2, result); // hit -> hot
    }

    @Test
    void singlePossiblePath() {
        String beginWord = "cat";
        String endWord = "dog";
        List<String> wordList = List.of("cot", "cog", "dog");

        int result = solution.ladderLength(beginWord, endWord, wordList);

        assertEquals(4, result); // cat -> cot -> cog -> dog
    }

    @Test
    void multiplePaths_choosesShortest() {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = List.of(
                "hot", "dot", "dog", "lot", "log", "cog", "hog"
        );

        int result = solution.ladderLength(beginWord, endWord, wordList);

        assertEquals(4, result); // hit -> hot -> hog -> cog
    }

    @Test
    void unreachableEvenThoughEndExists() {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = List.of("hat", "cat", "car", "bar", "cog");

        int result = solution.ladderLength(beginWord, endWord, wordList);

        assertEquals(0, result);
    }

    @Test
    void beginWordNotInWordList_stillWorks() {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = List.of("hot", "dot", "dog", "cog");

        int result = solution.ladderLength(beginWord, endWord, wordList);

        assertEquals(5, result); // hit -> hot -> dot -> dog -> cog
    }

    @Test
    void duplicateWordsInList() {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = List.of("hot", "dot", "dot", "dog", "cog");

        int result = solution.ladderLength(beginWord, endWord, wordList);

        assertEquals(5, result); // hit -> hot -> dot -> dog -> cog
    }

    @Test
    void oneLetterWords() {
        String beginWord = "a";
        String endWord = "c";
        List<String> wordList = List.of("a", "b", "c");

        int result = solution.ladderLength(beginWord, endWord, wordList);

        assertEquals(2, result); // a -> c
    }

    @Test
    void oneLetterWords_noPath() {
        String beginWord = "a";
        String endWord = "c";
        List<String> wordList = List.of("a", "b");

        int result = solution.ladderLength(beginWord, endWord, wordList);

        assertEquals(0, result);
    }

    @Test
    void minimalCase_twoWords() {
        String beginWord = "ab";
        String endWord = "ac";
        List<String> wordList = List.of("ac");

        int result = solution.ladderLength(beginWord, endWord, wordList);

        assertEquals(2, result); // ab -> ac
    }

    @Test
    void cycleLikeDictionary_doesNotLoop() {
        String beginWord = "aaa";
        String endWord = "bbb";
        List<String> wordList = List.of(
                "aab", "abb", "bbb", "aba", "baa"
        );

        int result = solution.ladderLength(beginWord, endWord, wordList);

        assertEquals(4, result); // aaa -> aab -> abb -> bbb
    }

    @Test
    void longerChain() {
        String beginWord = "toon";
        String endWord = "plea";
        List<String> wordList = List.of(
                "poon", "plee", "same", "poie", "plie", "poin", "plea", "toon"
        );

        int result = solution.ladderLength(beginWord, endWord, wordList);

        assertEquals(7, result); // toon -> poon -> poin -> poie -> plie -> plee -> plea
    }

    @Test
    void noIntermediateWords() {
        String beginWord = "abc";
        String endWord = "xyz";
        List<String> wordList = List.of("xyz");

        int result = solution.ladderLength(beginWord, endWord, wordList);

        assertEquals(0, result);
    }
}
