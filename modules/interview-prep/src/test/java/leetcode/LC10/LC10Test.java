package leetcode.LC10;

import leetcode.Prompt10;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class LC10Test {

    Solution sut = new Solution();



    @Test
    void exactMatch_true() {
        assertThat(sut.isMatch("abc", "abc")).isTrue();
    }

    @Test
    void exactMatch_false() {
        assertThat(sut.isMatch("abc", "ab")).isFalse();
    }

    @Test
    void dotMatchesSingleChar() {
        assertThat(sut.isMatch("a", ".")).isTrue();
    }

    @Test
    void dotDoesNotMatchMultipleChars() {
        assertThat(sut.isMatch("ab", ".")).isFalse();
    }

    @Test
    void dotInsidePattern() {
        assertThat(sut.isMatch("ab", "a.")).isTrue();
    }

    @Test
    void starMatchesZeroOccurrences() {
        assertThat(sut.isMatch("b", "a*b")).isTrue();
    }

    @Test
    void starMatchesManyOccurrences() {
        assertThat(sut.isMatch("aaaa", "a*")).isTrue();
    }

    @Test
    void starMatchesManyWithSuffix() {
        assertThat(sut.isMatch("aaab", "a*b")).isTrue();
    }

    @Test
    void dotStarMatchesAnything() {
        assertThat(sut.isMatch("abc", ".*")).isTrue();
    }

    @Test
    void fullStringRequired_notSubstring() {
        assertThat(sut.isMatch("abc", ".*b")).isFalse();
    }

    @Test
    void classicExample1() {
        assertThat(sut.isMatch("aa", "a")).isFalse();
    }

    @Test
    void classicExample2() {
        assertThat(sut.isMatch("aa", "a*")).isTrue();
    }

    @Test
    void classicExample3() {
        assertThat(sut.isMatch("ab", ".*")).isTrue();
    }

    @Test
    void classicExample4() {
        assertThat(sut.isMatch("aab", "c*a*b")).isTrue();
    }

    @Test
    void classicExample5() {
        assertThat(sut.isMatch("mississippi", "mis*is*p*.")).isFalse();
    }

    @Test
    void trickyCase1() {
        assertThat(sut.isMatch("aaa", "a*a")).isTrue();
    }

    @Test
    void trickyCase2() {
        assertThat(sut.isMatch("aaa", "ab*a*c*a")).isTrue();
    }

    @Test
    void trickyCase3() {
        assertThat(sut.isMatch("a", "ab*")).isTrue();
    }

    @Test
    void trickyCase4() {
        assertThat(sut.isMatch("bbbba", ".*a*a")).isTrue();
    }

    @Test
    void multipleStarsCase() {
        assertThat(sut.isMatch("aaa", "ab*ac*a")).isTrue();
    }

    @Test
    void emptyStringMatch() {
        assertThat(sut.isMatch("", "a*b*c*")).isTrue();
    }

    @Test
    void emptyPatternMatch() {
        assertThat(sut.isMatch("", "")).isTrue();
    }

    @Test
    void emptyPatternFails() {
        assertThat(sut.isMatch("a", "")).isFalse();
    }

    @Test
    void starBindingCheck() {
        assertThat(sut.isMatch("abbc", "ab*c")).isTrue();
    }
}
