package leetcode.LC55;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LC55Test {

    private Solution getSolution() {
        return new Solution(); // swap to Solution1 when you start your attempt
    }

    @Test
    void singleElement_isReachable() {
        assertTrue(getSolution().canJump(new int[]{0}));
    }

    @Test
    void simpleReachable() {
        assertTrue(getSolution().canJump(new int[]{2,3,1,1,4}));
    }

    @Test
    void simpleUnreachable() {
        assertFalse(getSolution().canJump(new int[]{3,2,1,0,4}));
    }

    @Test
    void zerosAtEndStillReachable() {
        assertTrue(getSolution().canJump(new int[]{2,0,2,0,1,0}));
    }

    @Test
    void longZeroEarlyBlocksProgress() {
        assertFalse(getSolution().canJump(new int[]{1,0,0,0}));
    }

    @Test
    void largeJumpSkipsGap() {
        assertTrue(getSolution().canJump(new int[]{1,4,0,0,0,0,1}));
    }
}
