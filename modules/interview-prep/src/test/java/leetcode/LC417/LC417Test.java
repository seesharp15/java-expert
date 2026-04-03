package leetcode.LC417;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;


public class LC417Test {

    private final Solution solution = new Solution3();

    @Test
    void exampleCase() {
        int[][] heights = {
                {1, 2, 2, 3, 5},
                {3, 2, 3, 4, 4},
                {2, 4, 5, 3, 1},
                {6, 7, 1, 4, 5},
                {5, 1, 1, 2, 4}
        };

        Set<String> expected = Set.of(
                "0,4",
                "1,3",
                "1,4",
                "2,2",
                "3,0",
                "3,1",
                "4,0"
        );

        var res = solution.pacificAtlantic(heights);
        var builder = new StringBuilder();

        for(var e: res) {

            builder.append(String.format("%s,%s\n", e.get(0), e.get(1)));

        }

        var d = builder.toString();

        assertEquals(expected, toSet(res));
    }

    @Test
    void singleCell() {
        int[][] heights = {
                {42}
        };

        Set<String> expected = Set.of("0,0");

        assertEquals(expected, toSet(solution.pacificAtlantic(heights)));
    }

    @Test
    void singleRowAllReachBoth() {
        int[][] heights = {
                {1, 2, 3, 4, 5}
        };

        Set<String> expected = Set.of(
                "0,0", "0,1", "0,2", "0,3", "0,4"
        );

        assertEquals(expected, toSet(solution.pacificAtlantic(heights)));
    }

    @Test
    void singleColumnAllReachBoth() {
        int[][] heights = {
                {1},
                {2},
                {3},
                {4},
                {5}
        };

        Set<String> expected = Set.of(
                "0,0", "1,0", "2,0", "3,0", "4,0"
        );

        assertEquals(expected, toSet(solution.pacificAtlantic(heights)));
    }

    @Test
    void allSameHeight_everyCellReachesBoth() {
        int[][] heights = {
                {7, 7, 7},
                {7, 7, 7},
                {7, 7, 7}
        };

        Set<String> expected = Set.of(
                "0,0", "0,1", "0,2",
                "1,0", "1,1", "1,2",
                "2,0", "2,1", "2,2"
        );

        assertEquals(expected, toSet(solution.pacificAtlantic(heights)));
    }

    @Test
    void increasingFromTopLeft_onlyBottomRowAndRightColReachBoth() {
        int[][] heights = {
                {1, 2, 3},
                {2, 3, 4},
                {3, 4, 5}
        };

        Set<String> expected = Set.of(
                "0,2",
                "1,2",
                "2,0", "2,1", "2,2"
        );

        assertEquals(expected, toSet(solution.pacificAtlantic(heights)));
    }

    @Test
    void decreasingFromTopLeft_onlyTopRowAndLeftColReachBoth() {
        int[][] heights = {
                {5, 4, 3},
                {4, 3, 2},
                {3, 2, 1}
        };

        Set<String> expected = Set.of(
                "0,0", "0,1", "0,2",
                "1,0",
                "2,0"
        );

        assertEquals(expected, toSet(solution.pacificAtlantic(heights)));
    }

    @Test
    void basinCenter_doesNotReachEitherBothSides() {
        int[][] heights = {
                {10, 10, 10},
                {10,  1, 10},
                {10, 10, 10}
        };

        Set<String> expected = Set.of(
                "0,0", "0,1", "0,2",
                "1,0",         "1,2",
                "2,0", "2,1", "2,2"
        );

        assertEquals(expected, toSet(solution.pacificAtlantic(heights)));
    }

    @Test
    void emptyGrid() {
        int[][] heights = {};

        List<List<Integer>> result = solution.pacificAtlantic(heights);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void oneByTwo() {
        int[][] heights = {
                {2, 1}
        };

        Set<String> expected = Set.of(
                "0,0", "0,1"
        );

        assertEquals(expected, toSet(solution.pacificAtlantic(heights)));
    }

    @Test
    void twoByOne() {
        int[][] heights = {
                {2},
                {1}
        };

        Set<String> expected = Set.of(
                "0,0", "1,0"
        );

        assertEquals(expected, toSet(solution.pacificAtlantic(heights)));
    }

    @Test
    void flatRingWithLowInterior_onlyRingCells() {
        int[][] heights = {
                {5, 5, 5, 5},
                {5, 1, 1, 5},
                {5, 1, 1, 5},
                {5, 5, 5, 5}
        };

        Set<String> expected = Set.of(
                "0,0", "0,1", "0,2", "0,3",
                "1,0",               "1,3",
                "2,0",               "2,3",
                "3,0", "3,1", "3,2", "3,3"
        );

        assertEquals(expected, toSet(solution.pacificAtlantic(heights)));
    }

    private Set<String> toSet(List<List<Integer>> coords) {
        return coords.stream()
                .map(cell -> cell.get(0) + "," + cell.get(1))
                .collect(Collectors.toSet());
    }

}
