package leetcode.LC433;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LC433Test {

    private final Solution solution = new Solution1();

    @Test
    void example1_oneMutation() {
        String start = "AACCGGTT";
        String end = "AACCGGTA";
        String[] bank = {"AACCGGTA"};

        assertEquals(1, solution.minMutation(start, end, bank));
    }

    @Test
    void example2_twoMutations() {
        String start = "AACCGGTT";
        String end = "AAACGGTA";
        String[] bank = {"AACCGGTA", "AACCGCTA", "AAACGGTA"};

        assertEquals(2, solution.minMutation(start, end, bank));
    }

    @Test
    void example3_threeMutations() {
        String start = "AAAAACCC";
        String end = "AACCCCCC";
        String[] bank = {"AAAACCCC", "AAACCCCC", "AACCCCCC"};

        assertEquals(3, solution.minMutation(start, end, bank));
    }

    @Test
    void returnsMinusOne_whenEndNotReachable() {
        String start = "AACCGGTT";
        String end = "AAACGGTA";
        String[] bank = {"AACCGATT", "AACCGATA", "AAACGATA"};

        assertEquals(-1, solution.minMutation(start, end, bank));
    }

    @Test
    void returnsMinusOne_whenEndGeneNotInBank() {
        String start = "AACCGGTT";
        String end = "AACCGGTA";
        String[] bank = {};

        assertEquals(-1, solution.minMutation(start, end, bank));
    }

    @Test
    void startEqualsEnd_shouldReturnZero() {
        String start = "AACCGGTT";
        String end = "AACCGGTT";
        String[] bank = {"AACCGGTA", "AACCGCTA", "AAACGGTA"};

        assertEquals(0, solution.minMutation(start, end, bank));
    }

    @Test
    void directMutationPreferred_overLongerPaths() {
        String start = "AACCGGTT";
        String end = "AACCGGTA";
        String[] bank = {
                "AACCGGTA",
                "AACCGATT",
                "AACCGATA",
                "AAACGATA"
        };

        assertEquals(1, solution.minMutation(start, end, bank));
    }

    @Test
    void multiplePossiblePaths_shouldReturnShortest() {
        String start = "AAAAACCC";
        String end = "CCCCCCCC";
        String[] bank = {
                "AAAACCCC",
                "AAAACCCC",
                "AAACCCCC",
                "AACCCCCC",
                "ACCCCCCC",
                "CCCCCCCC",
                "AAAACCCA",
                "AAACCCCA",
                "AACCCCCA"
        };

        assertEquals(5, solution.minMutation(start, end, bank));
    }

    @Test
    void bankWithUnusedGenes_shouldStillWork() {
        String start = "AACCGGTT";
        String end = "AAACGGTA";
        String[] bank = {
                "AACCGGTA",
                "AACCGCTA",
                "AAACGGTA",
                "TTTTTTTT",
                "CCCCCCCC"
        };

        assertEquals(2, solution.minMutation(start, end, bank));
    }

    @Test
    void noValidIntermediateEvenThoughEndExists_shouldReturnMinusOne() {
        String start = "AAAAACCC";
        String end = "CCCCCCCC";
        String[] bank = {
                "CCCCCCCC"
        };

        assertEquals(-1, solution.minMutation(start, end, bank));
    }

    @Test
    void handlesDuplicateEntriesInBank() {
        String start = "AACCGGTT";
        String end = "AACCGGTA";
        String[] bank = {"AACCGGTA", "AACCGGTA"};

        assertEquals(1, solution.minMutation(start, end, bank));
    }

    @Test
    void singleStepAmongManyNearMatches() {
        String start = "AAAAAAAA";
        String end = "AAAAAAAT";
        String[] bank = {
                "AAAAAAAT",
                "AAAAAATT",
                "AAAATAAT",
                "CCCCCCCC"
        };

        assertEquals(1, solution.minMutation(start, end, bank));
    }

    @Test
    void longerChain_allStepsRequired() {
        String start = "AAAAAAAA";
        String end = "CCCCCCCC";
        String[] bank = {
                "CAAAAAAA",
                "CCAAAAAA",
                "CCCAAAAA",
                "CCCCAAAA",
                "CCCCCAAA",
                "CCCCCCAA",
                "CCCCCCCA",
                "CCCCCCCC"
        };

        assertEquals(8, solution.minMutation(start, end, bank));
    }
}