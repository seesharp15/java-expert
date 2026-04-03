package leetcode.LC269;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.*;

public class LC269Test {

    private final Solution solution = new Solution2();

    @Test
    void exampleCase_shouldReturnValidOrdering() {
        String[] words = {"wrt", "wrf", "er", "ett", "rftt"};

        String result = solution.alienOrder(words);

        assertFalse(result.isEmpty());
        assertTrue(isValidAlienOrder(words, result));
        assertEquals(5, result.length()); // w,e,r,t,f
    }

    @Test
    void simpleTwoLetterOrdering() {
        String[] words = {"z", "x"};

        String result = solution.alienOrder(words);

        assertFalse(result.isEmpty());
        assertTrue(isValidAlienOrder(words, result));
        assertEquals(2, result.length());
    }

    @Test
    void cycle_shouldReturnEmptyString() {
        String[] words = {"z", "x", "z"};

        String result = solution.alienOrder(words);

        assertEquals("", result);
    }

    @Test
    void invalidPrefix_shouldReturnEmptyString() {
        String[] words = {"abc", "ab"};

        String result = solution.alienOrder(words);

        assertEquals("", result);
    }

    @Test
    void singleWord_shouldReturnAllUniqueChars() {
        String[] words = {"abc"};

        String result = solution.alienOrder(words);

        assertEquals(3, result.length());
        assertTrue(containsExactlyChars(result, Set.of('a', 'b', 'c')));
    }

    @Test
    void disconnectedCharacters_shouldStillIncludeAllCharacters() {
        String[] words = {"ab", "adc"};

        String result = solution.alienOrder(words);

        assertFalse(result.isEmpty());
        assertTrue(containsExactlyChars(result, Set.of('a', 'b', 'd', 'c')));
        assertTrue(isValidAlienOrder(words, result));
    }

    @Test
    void duplicateWords_shouldStillWork() {
        String[] words = {"abc", "abc"};

        String result = solution.alienOrder(words);

        assertFalse(result.isEmpty());
        assertTrue(containsExactlyChars(result, Set.of('a', 'b', 'c')));
        assertTrue(isValidAlienOrder(words, result));
    }

    @Test
    void multipleValidAnswers_shouldAcceptAnyValidOne() {
        String[] words = {"za", "zb", "ca", "cb"};

        String result = solution.alienOrder(words);

        assertFalse(result.isEmpty());
        assertTrue(isValidAlienOrder(words, result));
        assertTrue(containsExactlyChars(result, Set.of('z', 'a', 'b', 'c')));
    }

    private boolean containsExactlyChars(String result, Set<Character> expectedChars) {
        if (result.length() != expectedChars.size()) return false;

        Set<Character> actual = new HashSet<>();
        for (char c : result.toCharArray()) {
            actual.add(c);
        }
        return actual.equals(expectedChars);
    }

    private boolean isValidAlienOrder(String[] words, String order) {
        Map<Character, Integer> rank = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            rank.put(order.charAt(i), i);
        }

        for (String word : words) {
            for (char c : word.toCharArray()) {
                if (!rank.containsKey(c)) {
                    return false;
                }
            }
        }

        for (int i = 0; i < words.length - 1; i++) {
            if (compare(words[i], words[i + 1], rank) > 0) {
                return false;
            }
        }

        return true;
    }

    private int compare(String a, String b, Map<Character, Integer> rank) {
        int len = Math.min(a.length(), b.length());

        for (int i = 0; i < len; i++) {
            char ca = a.charAt(i);
            char cb = b.charAt(i);
            if (ca != cb) {
                return Integer.compare(rank.get(ca), rank.get(cb));
            }
        }

        return Integer.compare(a.length(), b.length());
    }
}