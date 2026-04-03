package leetcode.LC4;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/*
* 4. Median of Two Sorted Arrays
Hard

Topics
premium lock icon
Companies
Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.

The overall run time complexity should be O(log (m+n)).



Example 1:

Input: nums1 = [1,3], nums2 = [2]
Output: 2.00000
Explanation: merged array = [1,2,3] and median is 2.
Example 2:

Input: nums1 = [1,2], nums2 = [3,4]
Output: 2.50000
Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.


Constraints:

nums1.length == m
nums2.length == n
0 <= m <= 1000
0 <= n <= 1000
1 <= m + n <= 2000
-106 <= nums1[i], nums2[i] <= 106
* */
public class Solution {

    public double findMedianIndexTwoArrays(int[] nums1, int[] nums2) {
        var left = nums1.length;
        var right = nums2.length - nums1.length;
        var m = left + right;
        var med = (m % 2 == 0 ? m + .5 : m);

        return med / 2;
    }

//    public int[] findMedianIndexesOfTwoArrays(int[] nums1, int[] nums2) {
//        nums1 = nums1 == null ? new int[0] : nums1;
//        nums2 = nms2 == null ? new int[0] : nums2;
//
//        if (nums1.length == 0 && nums2.length == 0) return new int[]{0};
//        var left = nums1.length;
//        var right = nums2.length - nums1.length;
//        var m = left + right;
//
//
//        if (m % 2 == 0) {
//            return new int[]{m, m + 1};
//        }
//        return new int[]{m};
//    }
//
//


    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) return 0;

        var total = nums1.length + nums2.length;
        var target = total/2;


        int i = 0, j = 0;
        int prev = 0, curr = 0;

        for (var count = 0; count < total; count++) {
            prev = curr;

            if (i < nums1.length && (j >= nums2.length || nums1[i] <= nums2[j])) {
                curr = nums1[i];
                i++;
            } else {
                curr = nums2[j];
                j++;
            }
        }

        if (total % 2 == 1) {
            return curr;
        }

        return (prev + curr)/2D;

    }
}
        //nums1

        //build a priority queue
        //median will be in location (nums1.length + nums2.length) / 2;

        //since the arrays are sorted, we don't have to scan the whole thing
        //if current index of the q > median Index, stop

        //var q = new PriorityQueue<Integer>();
//
//        var l1 = nums1.length;
//        var l2 = nums2.length;
//
//        var medianIndex = (l1 + (l2 - l1)) + 1 / 2D;
//
//        //var medianIndex = findMedianIndexTwoArrays(nums1, nums2);
//        var maxIndex = Math.ceil(medianIndex); //medianIndexes[medianIndexes.length - 1];
//
//
//        var i1 = 0;
//        var i2 = 0;
//        var n1 = 0;
//        var n2 = 0;
//
//        var cn = 0;
//        var pn = 0;
//
//
//
//
//        while ((i1 + i2) <= maxIndex) {
//
//            pn = cn;
//
//            n1 = nums1[i1];
//            n2 = nums2[i2];
//
//            if (n1)
//
//
//            //if we "ran out" of array 1, we shouldn't even be considering it
//
//            pn = cn;
//            if (n1 > n2) {
//                cn = n2;
//                i2++;
//            } else if (n1 < n2) {
//                cn = n1;
//                i1++;
//            } else {
//                //check which array has room, pick it at random
//                if (i1 < nums1.length) {
//                    cn = n2;
//                    i1++;
//                    continue;
//                } else if (i2 < nums2.length) {
//                    cn = n1;
//                    i2++;
//                    continue;
//                } else {
//                    //pretty sure this would never happen
//                    throw new RuntimeException("Index is outside of bounds - this is unexpected");
//                }
//            }
//        }
//
//        if (medianIndex < maxIndex) {
//            var ttl = cn + pn;
//            var ttl2 = cn + (pn - cn);
//
//            return cn + (pn - cn) / 2D;
//        }
//
//        return cn;

//            }
//            else if (i1 < nums1.length) {
//                var n1 = nums1[index];
//                var n2 = nums2[index];
//                if (n1 > n2) {
//                    n
//                }
//
//            }else if (index < nums2.length) {
//                var n1 = nums1[index];
//                var n2 = nums2[index];
//            }
//
//            index += 1;
//        }
//
//        return 1;


