package leetcode.LC4;

public class Solution2 extends Solution {


    @Override
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) return 0;
        var totalLength = nums1.length + nums2.length;
        var isEven = totalLength % 2 == 0;
        if (!isEven) {
            totalLength--;
        }
        var medianIndex = totalLength / 2D;

        var i1 = 0;
        var i2 = 0;

        var counter = 0;
        var previous = 0;
        var current = 0;
        while (i1 < nums1.length || i2 < nums2.length) {

            if (i1 >= nums1.length) {
                current = nums2[i2++];
            } else if (i2 >= nums2.length) {
                current = nums1[i1++];
            } else if (nums1[i1] < nums2[i2]) {
                current = nums1[i1++];
            } else {
                current = nums2[i2++];
            }

            if (counter == medianIndex) {
                if (isEven) {
                    return (current + previous) / 2D;
                }
                return current;
            }

            counter++;
            previous = current;
        }

        throw new RuntimeException("This should not happen");
    }
}
