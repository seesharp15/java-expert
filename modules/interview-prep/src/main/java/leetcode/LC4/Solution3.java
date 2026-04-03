package leetcode.LC4;

public class Solution3 extends Solution {





    @Override
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        if (nums1 == null || nums2 == null) return 0;

        if (nums1.length > nums2.length){
            return findMedianSortedArrays(nums2, nums1);
        }

        var totalLength = nums1.length + nums2.length;
        var halfLength = (totalLength + 1) / 2;

        var left = 0;
        var right = nums1.length;

        while(left <= right) {
            var i = left + (right - left) / 2;  //partition index in nums1
            var j = halfLength - i;             //partition index in nums2

            var maxLeft1 = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            var minRight1 = (i == nums1.length) ? Integer.MAX_VALUE : nums1[i];

            var maxLeft2 = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            var minRight2 = (j == nums2.length) ? Integer.MAX_VALUE : nums2[j];

            if (maxLeft1 <= minRight2 && maxLeft2 <= minRight1) {
                if (totalLength % 2 == 0) {
                    return (Math.max(maxLeft1, maxLeft2) + Math.min(minRight1, minRight2)) / 2D;
                } else {
                    return Math.max(maxLeft1, maxLeft2);
                }
            } else if (maxLeft1 > minRight2) {
                right = i - 1;
            } else {
                left = i + 1;
            }

        }

        throw new IllegalArgumentException("arrays are not sorted!");
    }
}
