package leetcode.LC4;

public class Solution1 extends Solution {

    @Override
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) return 0;

        var medianTarget = nums1.length + nums2.length; // - 1;
//
//[0,1,2,3,4,|5|,6,7,8,9,10]
//[0,1,2,3,4,|5,6|,7,8,9,10,11]
        //[1,2,3,4,5,6,7,8]
         var isOdd = medianTarget % 2D != 0;
        if (isOdd) medianTarget--; //we'll do a final check to make sure that we do the average of medianTarget-1
        var median = (medianTarget / 2) ; //+ 1;//- 1;

        var i1 = 0;
        var i2 = 0;
        var prev = 0;
        for(var i =0; i <= median;i++) {

            var numa = nums1[i1];
            var numb = nums2[i2];
            Integer winner;

            var isMedian = i1 + i2 == median;

            if (numa < numb) {
                winner = numa;
                i1++;
            } else {
                winner = numb;
                i2++;
            }

            if (isMedian) {
                if (!isOdd) return (prev + winner) / 2D;
                return winner;
            }
            prev = winner;
        }
        throw new RuntimeException("Should not happen");
    }
}
