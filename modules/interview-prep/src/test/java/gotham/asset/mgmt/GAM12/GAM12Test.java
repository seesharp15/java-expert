package gotham.asset.mgmt.GAM12;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GAM12Test {

    private Solution getSolution() {
        return new Solution1(); // swap to Answer to verify
    }

    @Test
    void testCyclicExample() {
        // {2,3,1,0,4}: 0->2->1->3->0->2, exits after 5 steps with idx=2
        assertEquals(2, getSolution().dance(new int[]{2, 3, 1, 0, 4}));
    }

    @Test
    void testReachesEnd() {
        // {1,2,3,4,0}: 0->1->2->3->4, idx==4==length-1, return 4
        assertEquals(4, getSolution().dance(new int[]{1, 2, 3, 4, 0}));
    }

    @Test
    void testSingleElement() {
        // arr={0}: idx=0 == length-1, loop never enters, return 0
        assertEquals(0, getSolution().dance(new int[]{0}));
    }

    @Test
    void testImmediateEnd() {
        // {1, 0}: idx=0, arr.length-1=1. idx!=1, step: idx=arr[0]=1, now idx==1, return 1
        assertEquals(1, getSolution().dance(new int[]{1, 0}));
    }

    @Test
    void testSelfLoop() {
        // {0, 2, 1}: idx=0, arr[0]=0, cycles on 0. After 3 steps, return 0
        assertEquals(0, getSolution().dance(new int[]{0, 2, 1}));
    }

    @Test
    void testNullArray() {
        assertEquals(-1, getSolution().dance(null));
    }

    @Test
    void testEmptyArray() {
        assertEquals(-1, getSolution().dance(new int[]{}));
    }

    @Test
    void testDirectJumpToEnd() {
        // {2, 0, 1}: idx=0, arr[0]=2, idx=2==length-1, return 2
        assertEquals(2, getSolution().dance(new int[]{2, 0, 1}));
    }
}
