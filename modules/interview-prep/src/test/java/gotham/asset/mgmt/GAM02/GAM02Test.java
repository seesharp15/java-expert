package gotham.asset.mgmt.GAM02;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GAM02Test {

    private Solution getSolution() {
        return new Solution1(); // swap to Answer to verify
    }

    @Test
    void testReversesArray() {
        int[] a = {1, 2, 3, 4, 5};
        getSolution().transform(a);
        assertArrayEquals(new int[]{5, 4, 3, 2, 1}, a);
    }

    @Test
    void testReversesEvenLength() {
        int[] a = {1, 2, 3, 4};
        getSolution().transform(a);
        assertArrayEquals(new int[]{4, 3, 2, 1}, a);
    }

    @Test
    void testSingleElement() {
        int[] a = {42};
        getSolution().transform(a);
        assertArrayEquals(new int[]{42}, a);
    }

    @Test
    void testTwoElements() {
        int[] a = {1, 2};
        getSolution().transform(a);
        assertArrayEquals(new int[]{2, 1}, a);
    }

    @Test
    void testAlreadyReversed() {
        int[] a = {5, 4, 3, 2, 1};
        getSolution().transform(a);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, a);
    }

    @Test
    void testPalindrome() {
        int[] a = {1, 2, 3, 2, 1};
        getSolution().transform(a);
        assertArrayEquals(new int[]{1, 2, 3, 2, 1}, a);
    }

    @Test
    void testNegativeNumbers() {
        int[] a = {-3, -1, -4, -1, -5};
        getSolution().transform(a);
        assertArrayEquals(new int[]{-5, -1, -4, -1, -3}, a);
    }
}
