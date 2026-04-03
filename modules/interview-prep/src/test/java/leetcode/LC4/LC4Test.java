package leetcode.LC4;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LC4Test {


    private Solution getSolution() {

        return new Solution3();

    }
//
//    @Test
//    void testMedian_odd() {
//        double result = getSolution().findMedianIndexTwoArrays(
//                new int[]{1, 3},
//                new int[]{2}
//        );
//
//        assertEquals(1.0, result);
//    }
//    @Test
//    void testMedian_even() {
//        double result = getSolution().findMedianIndexTwoArrays(
//                new int[]{1, 3},
//                new int[]{2, 4}
//        );
//
//        assertEquals(2.5, result);
//    }

    @Test
    void tenElementsEachArray() {

        int[] nums1 = {1,3,5,7,9,11,13,15,17,19};
        int[] nums2 = {2,4,6,8,10,12,14,16,18,20};

        double result = getSolution().findMedianSortedArrays(nums1, nums2);

        assertEquals(10.5, result, 1e-9);
    }

    @Test
    void twoElementsVsTenElements() {
        int[] nums1 = {3, 7};
        int[] nums2 = {1,2,4,5,6,8,9,10,11,12};

        double result = getSolution().findMedianSortedArrays(nums1, nums2);

        assertEquals(6.5, result, 1e-9);
    }


    @Test
    void example1() {
        double result = getSolution().findMedianSortedArrays(
                new int[]{1, 3},
                new int[]{2}
        );

        assertEquals(2.0, result, 1e-9);
    }

    @Test
    void example2() {
        double result = getSolution().findMedianSortedArrays(
                new int[]{1, 2},
                new int[]{3, 4}
        );

        assertEquals(2.5, result, 1e-9);
    }

    @Test
    void oneArrayEmpty_oddTotalLength() {
        double result = getSolution().findMedianSortedArrays(
                new int[]{},
                new int[]{1, 2, 3}
        );

        assertEquals(2.0, result, 1e-9);
    }

    @Test
    void oneArrayEmpty_evenTotalLength() {
        double result = getSolution().findMedianSortedArrays(
                new int[]{},
                new int[]{1, 2, 3, 4}
        );

        assertEquals(2.5, result, 1e-9);
    }

    @Test
    void firstArraySingleElement_secondArraySingleElement() {
        double result = getSolution().findMedianSortedArrays(
                new int[]{1},
                new int[]{2}
        );

        assertEquals(1.5, result, 1e-9);
    }

    @Test
    void allElementsInFirstArraySmallerThanSecond() {
        double result = getSolution().findMedianSortedArrays(
                new int[]{1, 2, 3},
                new int[]{4, 5, 6}
        );

        assertEquals(3.5, result, 1e-9);
    }

    @Test
    void allElementsInSecondArraySmallerThanFirst() {
        double result = getSolution().findMedianSortedArrays(
                new int[]{4, 5, 6},
                new int[]{1, 2, 3}
        );

        assertEquals(3.5, result, 1e-9);
    }

    @Test
    void arraysWithDuplicates() {
        double result = getSolution().findMedianSortedArrays(
                new int[]{1, 2, 2},
                new int[]{2, 2, 3}
        );

        assertEquals(2.0, result, 1e-9);
    }

    @Test
    void arraysWithZeros() {
        double result = getSolution().findMedianSortedArrays(
                new int[]{0, 0},
                new int[]{0, 0}
        );

        assertEquals(0.0, result, 1e-9);
    }

    @Test
    void arraysWithNegativeNumbers() {
        double result = getSolution().findMedianSortedArrays(
                new int[]{-5, -3, -1},
                new int[]{-2, 0, 2}
        );

        assertEquals(-1.5, result, 1e-9);
    }

    @Test
    void veryDifferentLengths() {
        double result = getSolution().findMedianSortedArrays(
                new int[]{1, 2},
                new int[]{3, 4, 5, 6, 7, 8, 9}
        );

        assertEquals(5.0, result, 1e-9);
    }

    @Test
    void medianFallsExactlyOnPartitionBoundary() {
        double result = getSolution().findMedianSortedArrays(
                new int[]{1, 3, 8},
                new int[]{7, 9, 10, 11}
        );

        assertEquals(8.0, result, 1e-9);
    }

    @Test
    void oddTotalLength_withInterleavingArrays() {
        double result = getSolution().findMedianSortedArrays(
                new int[]{1, 4, 7},
                new int[]{2, 3, 5, 6, 8}
        );

        assertEquals(4.5, result, 1e-9);
    }

    @Test
    void oneElementAndManyElements() {
        double result = getSolution().findMedianSortedArrays(
                new int[]{100},
                new int[]{1, 2, 3, 4, 5, 6, 7}
        );

        assertEquals(4.5, result, 1e-9);
    }

    @Test
    void repeatedValuesAroundMedian() {
        double result = getSolution().findMedianSortedArrays(
                new int[]{1, 2, 2, 2},
                new int[]{2, 2, 3, 4}
        );

        assertEquals(2.0, result, 1e-9);
    }

}
