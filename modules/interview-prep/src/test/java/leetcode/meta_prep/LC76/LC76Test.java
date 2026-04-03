package leetcode.meta_prep.LC76;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LC76Test {

    private Solution getSolution() {
        return new Solution7(); // swap to Solution1 for your attempt
    }

    @Test
    void example1() {
        assertEquals("BANC", getSolution().minWindow("ADOBECODEBANC", "ABC"));
    }

    @Test
    void noWindowReturnsEmptyWhenTLongerThanS() {
        assertEquals("", getSolution().minWindow("A", "AA"));
    }

    @Test
    void emptySOrTEdgeCases() {
        assertEquals("", getSolution().minWindow("", "ABC"));
        assertEquals("", getSolution().minWindow("ABC", ""));
    }

    @Test
    void identicalStringsReturnSelf() {
        assertEquals("ABC", getSolution().minWindow("ABC", "ABC"));
    }

    @Test
    void singleCharTarget() {
        assertEquals("b", getSolution().minWindow("ab", "b"));
        assertEquals("", getSolution().minWindow("ab", "c"));
    }

    @Test
    void multipleOptimalWindowsChoosesSmallest() {
        assertEquals("ab", getSolution().minWindow("bdab", "ab"));
    }

    @Test
    void duplicatesInTargetRequireAllCounts() {
        assertEquals("AABBC", getSolution().minWindow("AAABBC", "AABC"));
    }

    @Test
    void denseAndSparseMix() {
        assertEquals("aec", getSolution().minWindow("cabefgecdaecf", "cae"));
    }

    @Test
    void caseSensitivity() {
        assertEquals("aBbBc", getSolution().minWindow("aAaBbBcC", "abc"));
    }
}
