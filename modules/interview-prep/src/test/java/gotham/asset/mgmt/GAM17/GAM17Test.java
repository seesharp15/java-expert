package gotham.asset.mgmt.GAM17;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GAM17Test {

    private Solution getSolution() {
        return new Solution1(); // swap to Answer to verify
    }

    @Test
    void testExampleCase() {
        int[] arr = {5, 3, 8, 1, 9, 2};
        getSolution().process(arr);
        assertArrayEquals(new int[]{3, 5, 1, 8, 2, 9}, arr);
    }

    @Test
    void testAlreadySorted() {
        int[] arr = {1, 2, 3, 4, 5};
        getSolution().process(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }

    @Test
    void testReverseSorted() {
        int[] arr = {5, 4, 3, 2, 1};
        getSolution().process(arr);
        // Single bubble pass: 4,3,2,1,5 -> but each adjacent pair swaps
        assertArrayEquals(new int[]{4, 3, 2, 1, 5}, arr);
    }

    @Test
    void testTwoElements() {
        int[] arr = {2, 1};
        getSolution().process(arr);
        assertArrayEquals(new int[]{1, 2}, arr);
    }

    @Test
    void testTwoElementsAlreadySorted() {
        int[] arr = {1, 2};
        getSolution().process(arr);
        assertArrayEquals(new int[]{1, 2}, arr);
    }

    @Test
    void testSingleElement() {
        int[] arr = {42};
        getSolution().process(arr);
        assertArrayEquals(new int[]{42}, arr);
    }

    @Test
    void testLargestMovesToEnd() {
        int[] arr = {3, 1, 4, 1, 5, 9, 2};
        getSolution().process(arr);
        // After one bubble pass, the max (9) stays at end
        assertArrayEquals(new int[]{1, 3, 1, 4, 5, 2, 9}, arr);
    }

    @Test
    void testAllEqual() {
        int[] arr = {7, 7, 7, 7};
        getSolution().process(arr);
        assertArrayEquals(new int[]{7, 7, 7, 7}, arr);
    }
}
