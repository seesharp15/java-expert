package leetcode.LC49;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LC49Test {

    private final Solution solution = new Solution1();

    @Test
    void example1_returnsExpectedGroups() {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};

        List<List<String>> result = solution.groupAnagrams(strs);

        assertGroupedAnagramsEqual(
                List.of(
                        List.of("eat", "tea", "ate"),
                        List.of("tan", "nat"),
                        List.of("bat")
                ),
                result
        );
    }

    @Test
    void singleEmptyString_returnsSingleGroup() {
        String[] strs = {""};

        List<List<String>> result = solution.groupAnagrams(strs);

        assertGroupedAnagramsEqual(
                List.of(List.of("")),
                result
        );
    }

    @Test
    void singleWord_returnsSingleGroup() {
        String[] strs = {"abc"};

        List<List<String>> result = solution.groupAnagrams(strs);

        assertGroupedAnagramsEqual(
                List.of(List.of("abc")),
                result
        );
    }

    @Test
    void noAnagrams_eachWordInOwnGroup() {
        String[] strs = {"abc", "def", "ghi"};

        List<List<String>> result = solution.groupAnagrams(strs);

        assertGroupedAnagramsEqual(
                List.of(
                        List.of("abc"),
                        List.of("def"),
                        List.of("ghi")
                ),
                result
        );
    }

    @Test
    void allWordsAreAnagrams_returnsSingleGroup() {
        String[] strs = {"abc", "bca", "cab", "cba"};

        List<List<String>> result = solution.groupAnagrams(strs);

        assertGroupedAnagramsEqual(
                List.of(List.of("abc", "bca", "cab", "cba")),
                result
        );
    }

    @Test
    void includesDuplicates_keepsDuplicateEntriesInSameGroup() {
        String[] strs = {"eat", "tea", "eat", "ate"};

        List<List<String>> result = solution.groupAnagrams(strs);

        assertGroupedAnagramsEqual(
                List.of(List.of("eat", "tea", "eat", "ate")),
                result
        );
    }

    @Test
    void mixedWordLengths_groupsOnlyTrueAnagrams() {
        String[] strs = {"a", "b", "ab", "ba", "abc", "cab", "xyz"};

        List<List<String>> result = solution.groupAnagrams(strs);

        assertGroupedAnagramsEqual(
                List.of(
                        List.of("a"),
                        List.of("b"),
                        List.of("ab", "ba"),
                        List.of("abc", "cab"),
                        List.of("xyz")
                ),
                result
        );
    }

    @Test
    void multipleEmptyStrings_groupedTogether() {
        String[] strs = {"", "", ""};

        List<List<String>> result = solution.groupAnagrams(strs);

        assertGroupedAnagramsEqual(
                List.of(List.of("", "", "")),
                result
        );
    }

    @Test
    void repeatedCharacters_groupedCorrectly() {
        String[] strs = {"aabb", "baba", "abba", "baab", "abcd"};

        List<List<String>> result = solution.groupAnagrams(strs);

        assertGroupedAnagramsEqual(
                List.of(
                        List.of("aabb", "baba", "abba", "baab"),
                        List.of("abcd")
                ),
                result
        );
    }

    @Test
    void wordsWithSameLettersDifferentCounts_notGroupedTogether() {
        String[] strs = {"aab", "abb", "baa", "bba"};

        List<List<String>> result = solution.groupAnagrams(strs);

        assertGroupedAnagramsEqual(
                List.of(
                        List.of("aab", "baa"),
                        List.of("abb", "bba")
                ),
                result
        );
    }

    @Test
    void oneCharacterWords_groupedCorrectly() {
        String[] strs = {"a", "a", "b", "b", "c"};

        List<List<String>> result = solution.groupAnagrams(strs);

        assertGroupedAnagramsEqual(
                List.of(
                        List.of("a", "a"),
                        List.of("b", "b"),
                        List.of("c")
                ),
                result
        );
    }

    private void assertGroupedAnagramsEqual(List<List<String>> expected, List<List<String>> actual) {
        assertEquals(toCanonicalSet(expected), toCanonicalSet(actual));
    }

    private Set<String> toCanonicalSet(List<List<String>> groups) {
        return groups.stream()
                .map(group -> group.stream().sorted().collect(Collectors.joining(",")))
                .collect(Collectors.toCollection(HashSet::new));
    }
}