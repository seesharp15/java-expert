package leetcode.meta_prep.LC207;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LC207Test {

    private Solution getSolution() {
        return new Solution1(); // swap to Solution1 for your attempt
    }

    @Test
    void simplePossible() {
        assertTrue(getSolution().canFinish(2, new int[][]{{1,0}}));
    }

    @Test
    void simpleCycle() {
        assertFalse(getSolution().canFinish(2, new int[][]{{1,0},{0,1}}));
    }

    @Test
    void disconnectedWithCycle() {
        assertFalse(getSolution().canFinish(3, new int[][]{{1,0},{0,1},{2,1}}));
    }
}
